package crud;

import entities.Category;
import entities.Options;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class CreateCategory {
    public static void main(String[] args) {
        // Введите название категории : Мониторы
        // Введите характеристики категории : Производитель, Диагональ, Матрица
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);
        try {
            manager.getTransaction().begin();
            Category category = new Category();
            System.out.println("Введите название категории: ");
            String input = scan.nextLine();
            TypedQuery<Category> categoryQuery = manager.createQuery(
                    "from Category c where c.name = name ", Category.class
            );
            List<Category> categories = categoryQuery.getResultList();
            for (Category c : categories) {
                while (true) {
                    if (c.getName().equals(input)) {
                        System.out.println("Название категории уже существует, повторите еще раз: ");
                        input = scan.nextLine();
                    } else {
                        category.setName(input);
                        break;
                    }
                }
            }
            manager.persist(category);
            System.out.println("Введите название характеристики: ");
            String createOptions = scan.nextLine();
            String[] parts = createOptions.split(",");
            for (String part : parts) {
                Options options = new Options();
                options.setName(part);
                options.setCategory(category);
                manager.persist(options);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        factory.close();
        manager.close();
        scan.close();
    }

}






