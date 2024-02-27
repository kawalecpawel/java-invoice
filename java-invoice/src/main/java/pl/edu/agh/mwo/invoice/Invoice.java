package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
    //private Map<Product, Integer> products;
    private Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw  new IllegalArgumentException("Produkt nie moze byc nullem");
        }
        this.products.add(product);
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        /*if (this.products == null) {
            this.products = new HashMap<>();
        }
        if (quantity <=0) {
            throw new IllegalArgumentException("Ilosc musi byc wieksza od");
        }

        if (product == null) {
            throw new IllegalArgumentException("Produkt nie moze byc nullem");
        }

        this.products.put(product, quantity);*/

        if (product == null) {
            throw new IllegalArgumentException("Produkt nie moze byc nullem");
            }
        this.products.add(product);
        this.products.add(product.quantity);
        // TODO: implement
    }

    public BigDecimal getNetPrice() {

        BigDecimal netPrice = BigDecimal.ZERO;

        if (products != null) {
            for (Product product : this.products) {
                netPrice = netPrice.add(product.getPrice());
            }
            return netPrice;
        } else {
            return BigDecimal.ZERO;
        }
    }
        /*if (products != null) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                netPrice = netPrice.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
            return netPrice;
        } else {
            return BigDecimal.ZERO;
        }
        }*/

    public BigDecimal getTax() {
        BigDecimal totalTax = BigDecimal.ZERO;

        if (products != null) {
            for (Product product : this.products) {
                totalTax = totalTax.add(product.getPrice().multiply(product.getTaxPercent()));
            }
            return totalTax;
        } else {
            return BigDecimal.ZERO;
        }
    }

        /*if (products != null) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                totalTax = totalTax.add(product.getPrice().multiply(product.getTaxPercent().multiply(BigDecimal.valueOf(quantity))));
            }
            return totalTax;
        } else {
            return BigDecimal.ZERO;
        }
    }*/

    public BigDecimal getGrossPrice () {
        BigDecimal grossPrice = BigDecimal.ZERO;

        if (products != null) {
            for (Product product : this.products) {
                grossPrice = grossPrice.add(product.getPriceWithTax());
            }
            return grossPrice;
        } else {
            return BigDecimal.ZERO;
        }
    }
}

        /*if (products !=  null) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                grossPrice = grossPrice.add(product.getPriceWithTax().multiply(BigDecimal.valueOf(quantity)));
            } return grossPrice;
        } else {
                return BigDecimal.ZERO;
            }
        }
    }*/

