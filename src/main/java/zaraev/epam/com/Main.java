package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("Старт программы");
        try (FileOutputStream fos = new FileOutputStream("test.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            String s1 = "Наша первая строка\n";
            String s2 = "Наша вторая строка";
            bos.write((s1.getBytes()));
            bos.write((s2.getBytes()));
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
