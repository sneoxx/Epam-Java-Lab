package com.zaraev.epam;

import com.zaraev.epam.annotation.Entity;
import com.zaraev.epam.annotation.Value;
import com.zaraev.epam.exceptions.NoValueAnnotationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Сервисный класс предоставляющий методы для работы с аннотациями
 */
public class ReflectionService {

    private final Logger log = LoggerFactory.getLogger(ReflectionService.class);

    /**
     * Проверка наличия аннотации Entity на классе
     *
     * @param object - экземпляр класса
     * @return - true в случаем наличия Entity на классе
     */
    public boolean checkEntityAnnotation(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.isAnnotationPresent(Entity.class)) {
            log.trace("checkEntityAnnotation() - Entity аннотация найдена");
            return true;
        }
        log.trace("checkEntityAnnotation() - Entity аннотация не найдена");
        return false;
    }


    /**
     * Проверка наличия аннотации Value на полях класса
     *
     * @param object - экземпляр класса
     * @return - true в случаем наличия Value на классе, иначе false
     */
    public boolean checkValueFieldsAnnotation(Object object) {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(Value.class)) {
                return false;
            }
            log.trace("checkValueFieldsAnnotation() - Найдена Value аннотация на поле: {}", field.getName());
        }
        return true;
    }

    /**
     * Проверка наличия аннотации Value в методах класса
     *
     * @param object - экземпляр класса
     * @return - true в случаем наличия Value в методах класса, иначе false
     */
    public boolean checkValueMethodsAnnotation(Object object) {
        Class<?> clazz = object.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Value.class)) {
                log.trace("checkValueMethodsAnnotation() - Найдена Value аннотация на методе: {}", method.getName());
                return true;
            }
        }
        log.trace("checkValueMethodsAnnotation() - Value аннотация на методе не найдена");
        return false;
    }

    /**
     * Проверка наличия аннотации Value в полях и методах класса
     *
     * @param object - экземпляр класса
     * @return - true в случае наличия Entity на классе и Value на всех полях или методах класса, иначе false
     * в случае наличия Entity на классе и Value не всех полях класса выкинет NoValueAnnotationException
     * в случае отстутсвия Entity на классе и пристутствия Value хотя бы на части полей выкинет IllegalStateException
     */
    public boolean checkValueFieldsAndMethodsAnnotations(Object object) {
        if (checkEntityAnnotation(object)) {
            if (checkValueFieldsAnnotation(object) && checkValueMethodsAnnotation(object)) {
                log.debug("checkValueFieldsAndMethodsAnnotations() - Все аннотации успешно получены");
                return true;
            } else {
                throw new NoValueAnnotationException("Entity аннотация найдена, но нет части Value аннотаций");
            }
        } else {
            if (checkValueFieldsAnnotation(object) || checkValueMethodsAnnotation(object)) {
                log.error("checkValueFieldsAndMethodsAnnotations() - Entity аннотация не найдена, хотя Value аннотации присутствуют");
                throw new IllegalStateException("Entity аннотация не найдена, хотя Value аннотации присутствуют");
            }
            log.debug("checkValueFieldsAndMethodsAnnotations() - Никаких аннотаций Value не найдено");
            return false;
        }
    }

    /**
     * Проверка наличия аннотации Value в полях и методах класса и последующая передача на заполение полей аннотацией Value
     * с обработкой возможных исключений
     *
     * @param object          - экземпляр класса
     * @param valueAnnotation - переданное значение value
     */
    public void checkAndFillFieldsWithValueAnnotation(Object object, Object valueAnnotation) {
        log.info("checkAndFillFieldsWithValueAnnotation() - На заполнение в объект: {} передано значение valueAnnotation: {}", object.getClass().getTypeName(), valueAnnotation);
        try {
            if (!checkValueFieldsAndMethodsAnnotations(object)) {
                return;
            } else {
                fillFieldsWithValueAnnotationDirectly(object, valueAnnotation);
                fillFieldsWithValueAnnotationViaSetter(object, valueAnnotation);
            }
        } catch (NoValueAnnotationException e) {
            log.error("checkAndFillFieldsWithValueAnnotation() - Entity аннотация найдена, но нет части аннотаций Value");
            fillFieldsWithValueAnnotationDirectly(object, valueAnnotation);
            fillFieldsWithValueAnnotationViaSetter(object, valueAnnotation);
        } catch (IllegalStateException e) {
            log.error("checkAndFillFieldsWithValueAnnotation() - Введен неверный аргумент", e);
            return;
        }
    }

    /**
     * Заполнение полей класса переданной аннотацией Value
     *
     * @param object          - экземпляр класса
     * @param valueAnnotation - переданное значение value
     */
    public void fillFieldsWithValueAnnotationDirectly(Object object, Object valueAnnotation) {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                if (Objects.nonNull(valueAnnotation) && field.getType().getName().equals(valueAnnotation.getClass().getTypeName())) {
                    setFieldValueAnnotation(object, valueAnnotation, field);
                    log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение переданным значением: {}", valueAnnotation);
                } else {
                    log.debug("fillFieldsWithValueAnnotationDirectly() - Переданный тип значения Value не соответствует полю класса: {}", field.getName());
                    Value defaultValueAnnotation = field.getAnnotation(Value.class);
                    if (Objects.nonNull(defaultValueAnnotation)) {
                        if (field.getType().getName().equals("java.lang.Integer")) {
                            int value = defaultValueAnnotation.valueInt();
                            log.debug("fillFieldsWithValueAnnotationDirectly() - Найдено дефолтное значение Value: {}", value);
                            setFieldValueAnnotation(object, value, field);
                            log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение поля: {} дефолтным значением: {}", field.getName(), value);
                        } else if ((field.getType().getName().equals("java.lang.String"))) {
                            String value = defaultValueAnnotation.value();
                            log.debug("fillFieldsWithValueAnnotationDirectly() - Найдено дефолтное значение Value: {}", value);
                            setFieldValueAnnotation(object, value, field);
                            log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение поля: {} дефолтным значением: {}}", field.getName(), value);
                        }
                    }
                }
            }
        }
    }

    /**
     * Заполнение полей класса через сеттер переданной аннотацией Value
     *
     * @param object          - экземпляр класса
     * @param valueAnnotation - переданное значение value
     */
    public void fillFieldsWithValueAnnotationViaSetter(Object object, Object valueAnnotation) {
        Class<?> clazz = object.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Value.class)) {
                Class[] parameter = method.getParameterTypes();
                String parameterType = parameter[0].getTypeName();
                if (Objects.nonNull(valueAnnotation) && parameterType.equals(valueAnnotation.getClass().getTypeName())) {
                    setFieldViaSetterValueAnnotation(object, valueAnnotation, method);
                    log.info("fillFieldsWithValueAnnotationViaSetter() - Успешное заполнение переданным значением: {} ", valueAnnotation);
                } else {
                    log.debug("fillFieldsWithValueAnnotationViaSetter() - Переданный тип значения Value не соответствует входному параметру метода: {}", method.getName());
                    Value defaultValueAnnotation = method.getAnnotation(Value.class);
                    if (Objects.nonNull(defaultValueAnnotation)) {
                        if (parameterType.equals("java.lang.Integer")) {
                            int value = defaultValueAnnotation.valueInt();
                            log.debug("fillFieldsWithValueAnnotationViaSetter() - Найдено дефолтное значение Value: {}", value);
                            setFieldViaSetterValueAnnotation(object, value, method);
                            log.info("fillFieldsWithValueAnnotationViaSetter() - Успешное заполнение дефолтным значением: {}", value);
                        } else if ((parameterType.equals("java.lang.String"))) {
                            String value = defaultValueAnnotation.value();
                            log.debug("fillFieldsWithValueAnnotationViaSetter() - Найдено дефолтное значение Value: {}", value);
                            setFieldViaSetterValueAnnotation(object, value, method);
                            log.info("fillFieldsWithValueAnnotationViaSetter() - Успешное заполнение дефолтным значением: {}", value);
                        }
                    }
                }
            }
        }
    }

    /**
     * Установка значения value в поле экземпляра класса
     *
     * @param object - экземпляр класса
     * @param value  - переданное значение value
     * @param field  - итерационная переменная
     */
    public void setFieldValueAnnotation(Object object, Object value, Field field) {
        field.setAccessible(true);
        try {
            field.set(object, value);
            log.info("setFieldValueAnnotation() - Поле: {} заполнено значением Value: {}", field.getName(), field.get(object));
        } catch (IllegalAccessException e) {
            log.error("setFieldValueAnnotation() - error", e);
        }
    }

    /**
     * Установка значения value в поле экземпляра класса через сеттер
     *
     * @param object - экземпляр класса
     * @param value  - переданное значение value
     * @param method - итерационная переменная
     */
    public void setFieldViaSetterValueAnnotation(Object object, Object value, Method method) {
        try {
            method.invoke(object, value);
            log.debug("setFieldViaSetterValueAnnotation() - В метод: {} отправлено значение Value: {}", method.getName(), value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("setFieldViaSetterValueAnnotation() - error", e);
        }
    }
}