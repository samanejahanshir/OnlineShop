import exceptions.InvalidInput;
import service.Shop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Shop shop = new Shop();
   static boolean exit=false;

    public static void main(String[] args) {
        System.out.println("---------- Welcome to Online Shop ----------");
        while (!exit) {
            System.out.println("1.Register\n2.Log in\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu){
                    case 1:
                    case 2:
                    case 3:
                    default:
                        throw new InvalidInput("enter 1 - 3 ");
                }


            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
