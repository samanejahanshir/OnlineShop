import exceptions.InvalidEmailExp;
import exceptions.InvalidInputExp;
import exceptions.InvalidNameExp;

import models.Address;
import models.User;
import service.Shop;
import service.UserService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static boolean exit = false;

    public static void main(String[] args) {
        System.out.println("---------- Welcome to Online Shop ----------");
        while (!exit) {
            System.out.println("1.Register\n2.Log in\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        getInputForRegister();
                    case 2:
                        getInputForLogIn();
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 - 3 ");
                }


            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getInputForRegister() {
        try {
            System.out.println("name :");
            String name = scanner.next();
            System.out.println("email :");
            String email = scanner.next();
            System.out.println("password:");
            String password = scanner.next();
            User user = new User(name, password, email, null);
            if (Shop.userDao.getUser(user) != -1) {
                System.out.println("** this user was exist ");
            } else {
                int id = Shop.userDao.setUser(user);
                if (id != -1) {
                    user.setId(id);
                }
                System.out.println("enter number of address : ");
                int number = scanner.nextInt();
                Set<Address> addresses = new HashSet<>();
                for (int i = 0; i < number; i++) {
                    System.out.println("city :");
                    String city = scanner.next();
                    System.out.println("street :");
                    String street = scanner.next();
                    System.out.println("postalCode :");
                    String postalCode = scanner.next();
                    System.out.println("tag :");
                    String tag = scanner.next();
                    addresses.add(new Address(city, postalCode, street, tag, user.getId()));
                }


                UserService userService = new UserService();
            }
        } catch (NumberFormatException | InputMismatchException | InvalidEmailExp | InvalidNameExp | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void getInputForLogIn() {

    }
}
