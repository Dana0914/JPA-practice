package crud;

import entities.Products;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class UpdateProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);
        try {
            manager.getTransaction().begin();
            System.out.println("Введите номер товара: ");
            long productId = scan.nextLong();
            scan.nextLine();
            Products product = manager.find(Products.class, productId);
            if (product != null) {
                System.out.printf("Введите новое название товара [%s]: ", product.getName());
                String productName = scan.nextLine();
                product.setName(productName);
                System.out.printf("Введите новую стоимость товара [%.2f]: ", product.getPrice());
                double price = scan.nextDouble();
                product.setPrice(price);
            } else {
                System.out.println("Сущность не найдена");
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
        factory.close();
    }
}

