package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "values")
public class Values {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "values")
    private String name;
    @ManyToOne
    @JoinColumn(name = "characteristics_id")
    private Options options;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private Products products;

    public long getId() {
        return id;
    }
    public void setOptions(Options options) {
        this.options = options;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Options getOptions() {
        return options;
    }

}
