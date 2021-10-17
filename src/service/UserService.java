package service;

import exceptions.InvalidInputExp;
import models.BuyStatus;
import models.Orders;
import models.Products;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);

    public void showMenu(int id) {
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
        boolean exit=false;
        System.out.println("---------------- Products ---------------");
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            System.out.println(listProduct.toString());
        }
        System.out.println("------------------------------------");
        while (!exit) {
            System.out.println("1.add to buy basket\n2.exit");
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1:
                    addToBuyBasket(id);
                    break;
                case 2:
                    exit=true;
                    break;
                default:
                    throw new InvalidInputExp("enter 1 or 2 for add to but basket or exit");
            }
        }
    }

    public void showBuyBasket(int id) throws SQLException {
        boolean exit = false;
        long totalPrice = 0;
        while (!exit) {
            List<Orders> ordersList = Shop.orderDao.getListOrders(id);
            if(ordersList.isEmpty()){
                System.out.println("-------------------------");
                System.out.println("your Buy Basket is empty . ");
                System.out.println("-------------------------");

                exit=true;
            }else {
                for (Orders orders : ordersList) {
                    Products products = Shop.productsDao.getProductById(orders.getProductId());
                    totalPrice += products.getPrice();
                    System.out.println("id order : " + orders.getId() + " " + products);
                }
                System.out.println("----------------------------");
                System.out.println("Total Price : " + totalPrice);
                System.out.println("----------------------------");

                System.out.println("1.Delete item from buy basket\n2.Confirmation buys\n3.Exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        deleteFromBuyBasket(id);
                        break;
                    case 2:
                        confirmBuyBasket(id,ordersList);
                        exit=true;
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 - 3 !");

                }
            }
        }
    }

    public void addToBuyBasket(int id) {
        try {
            List<Orders> orders=Shop.orderDao.getListOrders(id);
            if(orders.size()<5) {
                System.out.println("enter id of product : ");
                int idProduct = scanner.nextInt();
                Products products = Shop.productsDao.getProductById(idProduct);
                if (products != null && products.getStock()>0) {
                    Orders order = new Orders(id, idProduct, BuyStatus.WAITING.getTitle());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    order.setDate(now);
                    if (Shop.orderDao.setOrder(order) != -1) {
                        System.out.println("add to BuyBasket was successfully ");
                    } else {
                        System.out.println("Sorry Stock of this product is zero ! ");

                    }


                } else {
                    System.out.println("this product not found");
                }
            }else{
                System.out.println("your Buy Basket is full ! ");
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromBuyBasket(int id) {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("1.Delete item\n2.Exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        System.out.println("Enter id of order");
                        int idOrder = scanner.nextInt();
                        if (Shop.orderDao.getOrderById(idOrder) != -1) {
                            if (Shop.orderDao.deleteOrder(idOrder) != -1) {
                                System.out.println("Delete  item was successfully");
                            }
                        } else {
                            System.out.println("Order by this id not found ! ");
                        }
                        break;
                    case 2:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 or 2 !");
                }


            } catch (NumberFormatException | InputMismatchException | SQLException | InvalidInputExp e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void confirmBuyBasket(int id,List<Orders> ordersList) throws SQLException {

        if (Shop.orderDao.UpdateOrders(id) != -1) {
            System.out.println("Confirmation was successfully");
            for (Orders orders : ordersList) {
                Products products=Shop.productsDao.getProductById(orders.getProductId());
                Shop.productsDao.UpdateProduct(orders.getProductId(),products.getStock()-1);
            }
            System.out.println("Update stock of products");
            System.out.println("---------------------------------");

        } else {
            System.out.println("Confirmation was failed ! ");
        }

    }
}
