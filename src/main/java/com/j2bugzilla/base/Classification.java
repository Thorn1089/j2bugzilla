package com.j2bugzilla.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@code Classification} object represents a classification category on the Bugzilla installation. Each classification
 * has a unique ID, and each {@link Bug} is associated with exactly one {@code Classification}.
 *
 * @author grafuls
 *
 */
public class Classification {

    private final int id;

    private final String name;

    private String description;

    private List<Product> products = new ArrayList<Product>();

    /**
     * Creates a new {@link Classification} object with the specified unique ID and name.
     * @param id An {@code integer} representing the unique classification ID.
     * @param name A {@code String} representing the unique classification name.
     */
    public Classification(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of this {@link Classification}. If the classification was not retrieved from the webservice,
     * returns -1.
     * @return An integer representing the ID of this classification.
     */
    public int getID(){
        return id;
    }

    /**
     * Returns the name of this {@link Classification}.
     * @return A {@code String} representing the classification name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the description of this {@link Classification}.
     * @param description A {@code String} representing this classification's description.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Returns the description of this {@link Classification}.
     * @return A {@code String} describing this classification.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a list of products of this {@link Classification}.
     * @return A {@code List<Product>} whit the products for this classification.
     */
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    /**
     * Adds a product to this {@link Classification}.
     * @param product A {@code Product} representing a product for this classification.
     */
    public void addProduct(Product product){
        products.add(product);
    }
}
