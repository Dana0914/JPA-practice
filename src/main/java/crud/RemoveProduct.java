package crud;

import entities.Products;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class RemoveProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите номер товара: ");
        long productId = scan.nextLong();
        scan.nextLine();
        Products product = manager.find(Products.class, productId);
        try {
            manager.getTransaction().begin();
            System.out.println(product.getName());
            manager.remove(product);
            System.out.println("Сущность удалена");
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }

        manager.close();
        factory.close();
    }
}


