package zaraev.epam.com;

import java.util.HashMap;

/**
 *  Класс User с полями ФИО, Роль, которое является перечислением и содержит в себе ADMIN, USER, MODERATOR
 */
public class User {
    private String fio;
    private Role role;
    private HashMap<Role, String> roleMap;

    public User(String fio, Role role, HashMap map) {
        this.fio = fio;
        this.role = role;
        this.roleMap = map;
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

