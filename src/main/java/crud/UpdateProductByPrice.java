package crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UpdateProductByPrice {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        try {
        manager.getTransaction().begin();
        double perToIncrease = 7;
        Query increaseProductPriceByPer = manager.createQuery(
                "update Products p set p.price = p.price + (p.price * ?1 / 100) " +
                        "where p.category.id = ?2"
        );
        increaseProductPriceByPer.setParameter(1, perToIncrease);
        increaseProductPriceByPer.setParameter(2, 2);
        increaseProductPriceByPer.executeUpdate();
        manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException();
        }
        manager.close();
        factory.close();
    }
}
