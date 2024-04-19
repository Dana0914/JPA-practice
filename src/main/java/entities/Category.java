package entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> products;

    @OneToMany(mappedBy = "category")
    private List<Options> options;



    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }

    public List<Products> getProducts() {
        return products;
    }

    public List<Options> getOptions() {
        return options;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}
