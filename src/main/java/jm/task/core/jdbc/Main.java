package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User u1 = new User("u1_name", "u1_Lastame", (byte) 1);
        userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        System.out.println("User с именем – " + u1.getName() + " добавлен в базу данных");

        User u2 = new User("u2_name", "u2_Lastame", (byte) 2);
        userService.saveUser(u2.getName(), u2.getLastName(), u2.getAge());
        System.out.println("User с именем – " + u2.getName() + " добавлен в базу данных");

        User u3 = new User("u3_name", "u3_Lastame", (byte) 3);
        userService.saveUser(u3.getName(), u3.getLastName(), u3.getAge());
        System.out.println("User с именем – " + u3.getName() + " добавлен в базу данных");

        User u4 = new User("u4_name", "u4_Lastame", (byte) 4);
        userService.saveUser(u4.getName(), u4.getLastName(), u4.getAge());
        System.out.println("User с именем – " + u4.getName() + " добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        System.out.println(users);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
