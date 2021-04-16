package zaraev.epam.com;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j

public class MyClassLoader extends ClassLoader {
    final String PATHMYCLASSES = "C:/myClasses/";

    /**
     * Поиск указанного класса и создание экземпляра этого класса
     *
     * @param name - указанный класс
     * @return - экземпляр указанного класса
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //Проверяем, существует ли такой файл
        File className = new File(PATHMYCLASSES + name + ".class");
        if (!className.isFile())
            throw new ClassNotFoundException("Такого класса нет " + name);
        try (InputStream ins = new BufferedInputStream(new FileInputStream(className))) {
            //Cчитываем файл в массив байтов
            byte[] byteArray = new byte[(int) className.length()];
            ins.read(byteArray);
            log.debug("Класс успешно считан в массив байтов");
            Class clazz = defineClass(name, byteArray, 0, byteArray.length);
            log.debug("Массив байтов успешно преобразован в экземпляр класса");
            return clazz;
        } catch (Exception e) {
            log.error("Ошибки в байт коде", e);
            throw new ClassNotFoundException("Ошибки в байт коде");
        }
    }
}