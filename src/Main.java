import exceptions.InvalidEmailExp;
import exceptions.InvalidInputExp;
import exceptions.InvalidNameExp;
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
                        getInputForRegister();
                    case 2:
                        getInputForLogIn();
                    case 3:
                        exit=true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 - 3 ");
                }


            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void getInputForRegister(){
        try {
            String name = scanner.next();
            String email = scanner.next();
        }catch (InvalidEmailExp | InvalidNameExp e){
            System.out.println(e.getMessage());
        }

    }
    public static void getInputForLogIn(){

    }
}
