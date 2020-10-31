package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Ford", 1001);
        Car car2 = new Car("BMW", 2002);
        Car car3 = new Car("Mercedes", 3003);

        User user1 = new User("User1", "LastName1", "User1@mail.ru", car1);
        User user2 = new User("User2", "LastName2", "User2@mail.ru");
        userService.add(new User("User3", "LastName3", "User3@mail.ru", car3));

        user2.setCar(car2);

        userService.add(user1);
        userService.add(user2);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        users = userService.findUsersByCars("Ford", 1001);
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.printf("Car: model = %s;\n     series = %d \n", user.getCar().getModel(), user.getCar().getSeries());
            System.out.println();
        }

        context.close();
    }
}
