package service;

import exceptions.InvalidInputExp;
import models.*;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminService {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    public void showMenu() {
        while (!exit) {
            System.out.println("1.Add Product\n2.Delete Product\n3.Exit");
            try {
                int selectMenu = scanner.nextInt();
                switch (selectMenu) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        deleteProduct();
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

    public void addProduct() throws SQLException {
        System.out.println("enter number of product for add : ");
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("product's name : ");
            String name = scanner.next();
            System.out.println("price :");
            int price = scanner.nextInt();
            if (Shop.productsDao.getProduct(new Products(name, price)) != -1) {
                System.out.println("** this product is exist ");
            } else {
                System.out.println("stock of product : ");
                int stock = scanner.nextInt();
                System.out.println("select grouping : \n1.Electronic\n2.Shoes\n3.Reading");
                int groupNum = scanner.nextInt();
                String grouping;
                Products products;
                switch (groupNum) {
                    case 1:
                        grouping = Grouping.ELECTRONIC.getTitle();
                        break;
                    case 2:
                        grouping = Grouping.SHOES.getTitle();
                        break;
                    case 3:
                        grouping = Grouping.READING.getTitle();
                        break;
                    default:
                        throw new InvalidInputExp("==> enter 1 - 3 for grouping!");

                }
                products = new Products(name, price, stock, Grouping.valueOf(grouping));
                int idProduct = Shop.productsDao.setProduct(products);
                if (idProduct != -1) {
                    products.setIdProduct(idProduct);
                    switch (products.getGrouping()) {
                        case ELECTRONIC:
                            setElectronicProduct(products);
                            //TODO
                            break;
                        case SHOES:
                            setShoesProduct(products);
                            //TODO
                            break;
                        case READING:
                            setReadingProduct(products);
                            break;

                    }

                }

            }
        }


    }

    public void deleteProduct() {

    }

    public Electronics setElectronicProduct(Products products) {
                System.out.println("size : ");
                String size = scanner.next();
                System.out.println("pow : ");
                String pow = scanner.next();
                System.out.println("possibilities :");
                String possibilities = scanner.next();
                System.out.println("type :\n1.Television\n2.Radio");
                int selectType = scanner.nextInt();
                String type;
                switch (selectType) {
                    case 1:
                        type = ElectronicType.TELEVISION.getTitle();
                        break;
                    case 2:
                        type = ElectronicType.RADIO.getTitle();
                        break;
                    default:
                        throw new InvalidInputExp("select 1 or 2 for type electronic");

                }

       return new Electronics(products.getName(), products.getPrice(), products.getStock(), products.getGrouping(), size, pow, possibilities, ElectronicType.valueOf(type),products.getIdProduct());


    }

    public Shoes setShoesProduct(Products products) {
        System.out.println("size : ");
        String size = scanner.next();
        System.out.println("color : ");
        String color = scanner.next();
        System.out.println("type :\n1.Sport\n2.Official");
        int selectType = scanner.nextInt();
        String type;
        switch (selectType) {
            case 1:
                type = ShoesType.SPORT.getTitle();
                break;
            case 2:
                type = ShoesType.OFFICIAL.getTitle();
                break;
            default:
                throw new InvalidInputExp("select 1 or 2 for type electronic");

        }

        return new Shoes(products.getName(), products.getPrice(), products.getStock(), products.getGrouping(), size, color, ShoesType.valueOf(type),products.getIdProduct());


    }

    public Reading setReadingProduct(Products products) {
        System.out.println("size : ");
        String size = scanner.next();
        System.out.println("pages : ");
        int pages = scanner.nextInt();
        System.out.println("material : ");
        String material = scanner.next();
        System.out.println("type :\n1.Book\n2.Journal");
        int selectType = scanner.nextInt();
        String type;
        switch (selectType) {
            case 1:
                type = ReadingType.BOOK.getTitle();
                break;
            case 2:
                type = ReadingType.JOURNAL.getTitle();
                break;
            default:
                throw new InvalidInputExp("select 1 or 2 for type electronic");

        }

        return new Reading(products.getName(), products.getPrice(), products.getStock(), products.getGrouping(), pages,size,material, ReadingType.valueOf(type),products.getIdProduct());


    }
}