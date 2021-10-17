package service;

import exceptions.InvalidInputExp;
import models.BuyStatus;
import models.Orders;
import models.Products;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    boolean exit=false;
    Scanner scanner=new Scanner(System.in);
    public  void showMenu(int id){
        while (!exit) {
            System.out.println("1.Show list products\n2.show buy basket\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        showListProducts(id);
                        break;
                    case 2:
                        showBuyBasket(id);
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
    public void showListProducts(int id) throws SQLException {
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            System.out.println(listProduct.toString());
        }
        System.out.println("1.add to buy basket\n2.exit");
        int selectNum=scanner.nextInt();
        switch (selectNum){
            case 1:
                addToBuyBasket(id);
            case 2:
            default:
                throw new InvalidInputExp("enter 1 or 2 for add to but basket or exit");
        }

    }
    public  void showBuyBasket(int id) throws SQLException {
        Shop.orderDao.getListOrders(id);
    }
public  void addToBuyBasket(int id){
        try {
            System.out.println("enter id of product : ");
            int idProduct = scanner.nextInt();
            Products products=Shop.productsDao.getProductById(idProduct);
           if(products!=null){
               Orders orders=new Orders(id,idProduct, BuyStatus.WAITING.getTitle());
               if(Shop.orderDao.setOrder(orders)!=-1){
                   System.out.println("add to BuyBasket was successfully ");
               }else {
                   System.out.println("add to BuyBasket was failed ");

               }


           }else {
               System.out.println("this product not found");
           }
        }catch (NumberFormatException | SQLException e){
            System.out.println(e.getMessage());
        }
}
}
