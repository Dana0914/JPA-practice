package menu;

import entities.Category;
import entities.Options;
import entities.Products;
import entities.Values;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class ApplicationMain {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    public static EntityManager manager = factory.createEntityManager();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(
                """
                         -Создание товара [1]\s
                         -Изменение товара[2]\s
                         -Удаление товара[3] \
                        """);
        int n = 0;
        while (n != -1) {
            System.out.println("Выберите действие: ");
            n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case -1:
                    System.out.println("Программа завершена");
                    break;
                default:
                    System.out.println("Такой команды не существует");
                    break;
            }
        }
    }

    public static void createProduct() {
        try {
            manager.getTransaction().begin();
            System.out.println("Введите номер категории: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            Category category = manager.find(Category.class, id);
            if (category != null) {
                System.out.println("Введите названия товара: ");
                String createProducts = scanner.nextLine();
                Products products = new Products();
                products.setName(createProducts);
                System.out.println("Введите стоимость товара: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                products.setPrice(price);
                products.setCategory(category);
                manager.persist(products);

                List<Options> optionsList = category.getOptions();
                for (Options o : optionsList) {
                    System.out.println("Введите значения характеристик: ");
                    String createValues = scanner.nextLine();
                    Values values = new Values();
                    values.setName(createValues);
                    values.setOptions(o);
                    values.setProducts(products);
                    manager.persist(values);
                }
            } else {
                System.out.println("Сущность не найдена");
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }



    }

    public static void updateProduct() {
        try {
            manager.getTransaction().begin();
            System.out.println("Введите номер товара: ");
            long productId = scanner.nextLong();
            scanner.nextLine();
            Products product = manager.find(Products.class, productId);
            if (product != null) {
                System.out.printf("Введите новое название товара [%s]: ", product.getName());
                String productName = scanner.nextLine();
                Query updateName = manager.createQuery(
                        "update Products p set p.name = :name where p.id = :id"
                );
                updateName.setParameter("name", productName);
                updateName.setParameter("id", productId);
                System.out.printf("Введите новую стоимость товара [%.2f]: ", product.getPrice());
                long price = scanner.nextLong();
                scanner.nextLine();
                Query updatePrice = manager.createQuery(
                        "update Products p set p.price = :price where p.name = :name"
                );
                updatePrice.setParameter("price", price);
                updatePrice.setParameter("name", productName);
                updateName.executeUpdate();
                updatePrice.executeUpdate();
                manager.persist(product);
            } else {
                System.out.println("Сущность не найдена");
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    public static void removeProduct() {
        try {
            manager.getTransaction().begin();
            System.out.println("Введите имя товара для удаления: ");
            String productName = scanner.next();
            Query deleteProduct = manager.createQuery(
                    "delete from Products p where p.name = :name"
            );
            deleteProduct.setParameter("name", productName).executeUpdate();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}