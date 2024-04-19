package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "items")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Values> values;
    @Column(name = "item_names")
    private String name;

    @Column(name = "price")
    private double price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

    public List<Values> getValues() {
        return values;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
