package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс-фильтр комманд, определяет в какой обработчик должна быть передана команда.
 */
@Slf4j
public class CommandFilter {
    private String command = "1";

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void parseCommand() {
        try {
            if (command.startsWith("add")) {
                log.debug("Вы ввели команду {}", command);
                System.out.println("Вы ввели команду: " + command);
                CommandAddHandler myCommandAddHandler = new CommandAddHandler();
                myCommandAddHandler.add(command);
            } else if (command.startsWith("delete")) {
                log.debug("Вы ввели команду {}", command);
                System.out.println("Вы ввели команду: " + command);
                CommandDeleteHandler myCommandDeleteHandler = new CommandDeleteHandler();
                myCommandDeleteHandler.delete(command);
            } else if (command.startsWith("print")) {
                log.debug("Вы ввели команду {}", command);
                System.out.println("Вы ввели команду: " + command);
                CommandPrintHandler myCommandPrintHandler = new CommandPrintHandler();
                myCommandPrintHandler.print(command);
            } else {
                log.debug("Введена ошибочная команда {}", command);
                System.out.println("Введена ошибочная команда, исправьте и введите еще раз");
            }
        } catch (Exception e) {
            log.error("Ошибка инициализации класса", e);
        }
    }
}