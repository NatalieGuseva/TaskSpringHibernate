package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("audi", 1);
        Car car2 = new Car("skoda", 2);
        Car car3 = new Car("bmw", 3);
        Car car4 = new Car("cadilac", 4);

        userService.add(new User("Ivan", "Ivanov", "user1@mail.ru", car1));
        userService.add(new User("Petr", "Petrov", "user2@mail.ru", car2));
        userService.add(new User("Fedor", "Fedorov", "user3@mail.ru", car3));
        userService.add(new User("Sidr", "Sidorov", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        try {
            System.out.println(userService.getUserCar("lada", 3));

        } catch (NoResultException e) {
            System.out.println("User не найден");
        }

        context.close();
    }
}
