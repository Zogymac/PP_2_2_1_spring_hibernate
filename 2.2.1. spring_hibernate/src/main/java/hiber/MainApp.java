package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      List<User> userList = new ArrayList<>();
      userList.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userList.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userList.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userList.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<Car> cars = new ArrayList<>();
      cars.add(new Car(userList.get(0), "lada", 1));
      cars.add(new Car(userList.get(1), "lada", 2));
      cars.add(new Car(userList.get(2), "fiat", 240));
      cars.add(new Car(userList.get(3), "bmw", 5));

      for (int i = 0; i < userList.size(); i++) {
         userService.add(userList.get(i), cars.get(i));
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("User by car: " + userService.getUserByCar("lada", 1).getFirstName());



      context.close();
   }
}
