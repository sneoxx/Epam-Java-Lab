package zaraev.epam.com;

import java.util.HashMap;

/**
 *  Класс User с полями ФИО, Роль, которое является перечислением и содержит в себе ADMIN, USER, MODERATOR
 */
public class User {
    private String fio;
    private Role role;
    static private HashMap<Role, String> roleMap = new HashMap<>();

    static{
        roleMap.put(Role.ADMIN, "Полные админские права");
        roleMap.put(Role.USER, "Стандартные права пользователя");
        roleMap.put(Role.MODERATOR, "Права по модерированию контента");
    }

    public User(String fio, Role role) {
        this.fio = fio;
        this.role = role;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Вывести приветственное сообщение на основании роли пользователя
     * @param user - Обьект класса user
     */

    public void welcomeMessage(User user){
        System.out.println("Приветствуем Вас " + user.fio + ". Ваша роль - " + user.role + ". Вам предоставлены - " + roleMap.get(user.role));
    }
}

