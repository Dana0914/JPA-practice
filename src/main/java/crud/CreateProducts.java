package crud;

import entities.Category;
import entities.Options;
import entities.Products;
import entities.Values;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;


public class CreateProducts {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);
        try {
            manager.getTransaction().begin();
            System.out.println("Введите номер категории: ");
            long id = scan.nextLong();
            scan.nextLine();
            Category category = manager.find(Category.class, id);
            if (category == null) {
                System.out.println("Сущность не найдена");
            } else {
                System.out.println("Введите названия товара: ");
                String createProducts = scan.nextLine();
                Products products = new Products();
                products.setName(createProducts);
                System.out.println("Введите стоимость товара: ");
                double price = scan.nextDouble();
                scan.nextLine();
                products.setPrice(price);
                products.setCategory(category);
                manager.persist(products);

                List<Options> optionsList = category.getOptions();
                for (Options o : optionsList) {
                    System.out.println("Введите значения характеристик: ");
                    String createValues = scan.nextLine();
                    Values values = new Values();
                    values.setName(createValues);
                    values.setOptions(o);
                    values.setProducts(products);
                    manager.persist(values);
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        scan.close();
        manager.close();
        factory.close();
    }
}

