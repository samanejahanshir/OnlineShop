package service;

import exceptions.InvalidInputExp;
import models.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);
    public User user;

    public UserService(User user) {
        this.user = user;
    }

    public void showMenu() {
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

            } catch (InvalidInputExp | NumberFormatException | SQLException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }
    }

    public void showListProducts() throws SQLException {
        boolean exit = false;
        List<Products> listProducts = Shop.productsDao.getListProducts();
        if(listProducts.isEmpty()){
            System.out.println("there is nothing to show .");
        }
        else {
            System.out.println("---------------- Products ---------------");
            for (Products listProduct : listProducts) {
                System.out.println(listProduct.toString());
            }
            System.out.println("------------------------------------");
            while (!exit) {
                System.out.println("1.add to buy basket\n2.Show by grouping\n3.Show product's detail\n4.exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        addToBuyBasket();
                        break;
                    case 2:
                        showByGrouping();
                        break;
                    case 3:
                        showProductsDetail();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        throw new InvalidInputExp("enter 1 - 3 for add to but basket or exit");
                }
            }
        }
    }

    public void showBuyBasket() throws SQLException {
        boolean exit = false;
        while (!exit) {
            long totalPrice = 0;
            List<Orders> ordersList = Shop.orderDao.getListOrders(user.getId());
            if (ordersList.isEmpty()) {
                System.out.println("-------------------------");
                System.out.println("your Buy Basket is empty . ");
                System.out.println("-------------------------");

                exit = true;
            } else {
                System.out.println("number of orders : " + ordersList.size());
                System.out.println("---------------------------");
                for (Orders orders : ordersList) {
                    Products products = Shop.productsDao.getProductById(orders.getProducts().getId());
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
                        deleteFromBuyBasket();
                        break;
                    case 2:
                        confirmBuyBasket(ordersList);
                        exit = true;
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

    public void addToBuyBasket() {
        try {
            List<Orders> orders = Shop.orderDao.getListOrders(user.getId());
            if (orders.size() < 5) {
                System.out.println("enter id of product : ");
                int idProduct = scanner.nextInt();
                Products products = Shop.productsDao.getProductById(idProduct);
                if (products != null && products.getStock() > 0) {
                    Orders order = new Orders(BuyStatus.WAITING);
                    user.getOrders().add(order);
                    order.setUser(user);
                    order.setProducts(products);
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
            } else {
                System.out.println("your Buy Basket is full ! ");
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }
    }

    public void deleteFromBuyBasket() {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("1.Delete item\n2.Exit");
                int selectNum = scanner.nextInt();
                switch (selectNum) {
                    case 1:
                        System.out.println("Enter id of order");
                        int idProduct = scanner.nextInt();
                        Orders orders = Shop.orderDao.getOrderById(idProduct);
                        if (orders != null) {
                            if (Shop.orderDao.deleteOrder(idProduct) != -1) {

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
                scanner.nextLine();

            }
        }

    }

    public void confirmBuyBasket(List<Orders> ordersList) throws SQLException {
        for (Orders orders : ordersList) {
            Shop.orderDao.UpdateOrders(orders);
            Products products = Shop.productsDao.getProductById(orders.getProducts().getId());
            Shop.productsDao.UpdateProduct(orders.getProducts().getId(), products.getStock() - 1);
        }
        System.out.println("Update stock of products");
        System.out.println("buying was finished");
        System.out.println("---------------------------------");


    }

    public void showByGrouping() throws SQLException {
        System.out.println("Grouping : \n1.Electronics\n2.Shoes\n3.Reading\n4.exit");
        String type = "";
        try {
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1:
                    type = Grouping.ELECTRONIC.getTitle();
                    break;
                case 2:
                    type = Grouping.SHOES.getTitle();
                    break;
                case 3:
                    type = Grouping.READING.getTitle();
                    break;
                case 4:
                default:
                    throw new InvalidInputExp("enter 1 - 4 !");

            }
        } catch (InvalidInputExp | NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }
        System.out.println("---------------- Products ---------------");
        for (Products listProduct : Shop.productsDao.getListProducts()) {
            if (listProduct.getProductGroup().equals(type)) {
                System.out.println(listProduct);
            }
        }
        System.out.println("------------------------------------");

    }

    public void showProductsDetail() throws SQLException {
        boolean exit = false;
        System.out.println("enter id of product : ");
        try {
            int idProduct = scanner.nextInt();
            Products products = Shop.productsDao.getProductById(idProduct);
            if (products != null) {
                String detail = Shop.productsDao.getDetailProduct(products.getProductGroup(), idProduct);
                System.out.println("---------------  Detail  ---------------");
                System.out.println(detail);
                System.out.println("----------------------------------------");

            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();

        }


    }
}
