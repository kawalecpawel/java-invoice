package pl.edu.agh.mwo.invoice.product;

import org.hamcrest.Matchers;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;
    public Product quantity;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Product name cannot be null");
        }


        if (price == null || price.compareTo(BigDecimal.ZERO) == -1) {
            throw new java.lang.IllegalArgumentException("Product price cannot be null");
        }

        if (price == null) {
            throw new java.lang.IllegalArgumentException("Product price cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return this.price.multiply(this.taxPercent).add(this.price);
    }
}
