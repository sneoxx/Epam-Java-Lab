package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс-фильтр комманд, определяет в какой обработчик должна быть передана команда.
 */
@Slf4j
public class CommandFilter {
    private CommandAddHandler commandAddHandler;
    private CommandDeleteHandler commandDeleteHandler;
    private CommandPrintHandler commandPrintHandler;

    public CommandFilter() {
        this.commandAddHandler = new CommandAddHandler();
        this.commandDeleteHandler = new CommandDeleteHandler();
        this.commandPrintHandler = new CommandPrintHandler();
    }

    public void parseCommand(String command) {
        try {
            if (command.startsWith("add")) {
                System.out.println("Вы ввели команду: " + command);
                commandAddHandler.add(command);
            } else if (command.startsWith("delete")) {
                System.out.println("Вы ввели команду: " + command);
                commandDeleteHandler.delete(command);
            } else if (command.startsWith("print")) {
                System.out.println("Вы ввели команду: " + command);
                commandPrintHandler.print(command);
            } else {
                System.out.println("Введена ошибочная команда, исправьте и введите еще раз");
            }
        } catch (Exception e) {
            log.error("Ошибка инициализации класса", e);
        }
    }
}