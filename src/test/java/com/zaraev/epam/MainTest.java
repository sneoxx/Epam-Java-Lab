//package com.zaraev.epam;
//
//import com.zaraev.epam.pojo.Human;
//import org.junit.jupiter.api.Test;
//
//public class MainTest {
//    public static final String EXAMPLE_NAME = "Petr test";
//    public static final int EXAMPLE_AGE = 1;
//
//    // класс для тестирования
//    @Test
//    public void testCreateHuman (){
//        Human example = new Human();//1 блок теста, подготовка данных - создать класс
//
//        fillFieldsWithValueAnnotation(example);//2 блок теста - вызвать метод, который мы тестируем
//
//        assertEquals(example.getName(), EXAMPLE_NAME); //3 блок теста, проверка результата - проверить, что у класса заполнены два поля константами
//        assertEquals(example.getAge(), EXAMPLE_AGE);
//    }
//}
//}