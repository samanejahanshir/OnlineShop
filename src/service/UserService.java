package service;

import exceptions.InvalidInputExp;
import models.Products;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    boolean exit=false;
    Scanner scanner=new Scanner(System.in);
    public  void showMenu(){
        while (!exit) {
            System.out.println("1.Show list products\n2.show buy basket\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        showListProducts();
                        break;
                    case 2:
                        showBuyBasket();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("==> enter 1 - 3 ! ");
                }

            } catch (InvalidInputExp | NumberFormatException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void showListProducts() throws SQLException {
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            System.out.println(listProduct.toString());
        }

    }
    public  void showBuyBasket(){

    }

}
