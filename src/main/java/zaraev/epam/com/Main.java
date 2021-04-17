package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.debug("Старт программы");
        System.out.println("Программа для работы с файлами. \n"
                + "Введите команду в следующем формате: \n"
                + "add x fileName \"text\" для добавления текста в указанный файл \n"
                + "add fileName \"text\" для добавления текста в конец указанного файла\n"
                + "delete x fileName - для удаления строки из указанного файла \n"
                + "delete fileName - для удаления последней строки из указанного файла \n"
                + "print x fileName - для вывода на печать строки из указанного файла \n"
                + "print fileName - для вывода на печать указанного файла \n"
                + "exit - для завершения работы \n"
                + "где: x - номер строки, fileName - имя файла, \"text\" - текст для добавления в файл.\n");
        Scanner scanner = new Scanner(System.in); //создаем сканнер и направляем в него поток из консоли
        CommandFilter myCommandFilter = new CommandFilter(); // создаем экземпляр класса фильтр
        while (true) {
            System.out.println("Введите команду: "); // Выводим в консоль, что необходимо что то в нее ввести
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                return;
            }
            myCommandFilter.parseCommand(command);
            log.debug("Вы ввели команду {}", command);
        }
    }
}