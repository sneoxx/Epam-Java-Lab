package zaraev.epam.com;

import java.io.*;

public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //Проверяем, существует ли такой файл
        File className = new File(name+".class");
        if(!className.isFile())
            throw new ClassNotFoundException("Такого класса нет" + name);
        try(InputStream ins = new BufferedInputStream(new FileInputStream(className))){
            //Cчитываем файл в массив байтов
            byte[]byteArray = new byte[(int)className.length()];
            ins.read(byteArray);
            Class clazz = defineClass( byteArray, 0, byteArray.length);
            return clazz;
        }catch (Exception e){
            e.printStackTrace();
            throw new ClassNotFoundException("Ошибки в байт коде");
        }
    }
}