package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private static int invoiceCounter = 0; // Static variable to keep track of invoice numbers
    private int invoiceNumber;
    private Map<Product, Integer> products = new HashMap<>();
    private Collection<Product> products2 = new ArrayList<>();


    public Invoice() {
        invoiceCounter++;
        this.invoiceNumber = invoiceCounter;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Null product");
        }
        this.products2.add(product);
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0 || product == null) {
            throw new IllegalArgumentException("Invalid quantity or null product");
        }
        this.products.put(product, products.getOrDefault(product, 0) + quantity);
        //this.products2.add(product);
        // TODO: implement
    }

    public BigDecimal getNetTotal() {

        BigDecimal netPrice = BigDecimal.ZERO;
        for (Product product : this.products2) {
            netPrice = netPrice.add(product.getPrice());
        }
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            netPrice = netPrice.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return netPrice;

    }

    public BigDecimal getTaxTotal() {


        BigDecimal totalTax = BigDecimal.ZERO;

        for (Product product : products2) {
            BigDecimal tax = product.getPrice().multiply(product.getTaxPercent());
            totalTax = totalTax.add(tax);
        }

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {

            Product product = entry.getKey();
            int quantity = entry.getValue();

            BigDecimal tax = product.getPrice().multiply(product.getTaxPercent())
                    .multiply(BigDecimal.valueOf(quantity));
            totalTax = totalTax.add(tax);
        }

        return totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    public BigDecimal getGrossPrice() {


        BigDecimal totalGrossPrice = BigDecimal.ZERO;

        for (Product product : products2) {
            BigDecimal grossPrice = product.getPrice().add(product.getPrice()
                    .multiply(product.getTaxPercent()));
            totalGrossPrice = totalGrossPrice.add(grossPrice);
        }


        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal grossPrice = product.getPrice().add(product.getPrice()
                    .multiply(product.getTaxPercent()));

            grossPrice = grossPrice.multiply(BigDecimal.valueOf(quantity));
            totalGrossPrice = totalGrossPrice.add(grossPrice);
        }

        return totalGrossPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getSubtotal() {

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : products2) {
            subtotal = subtotal.add(product.getPrice());
        }
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return subtotal;

    }


    public BigDecimal getGrossTotal() {
        return getGrossPrice();
    }

    public int getNumber() {
        return this.invoiceNumber;
    }

    public String getInvoiceDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice Number: ").append(invoiceNumber).append("\n");

        int positionCount = 0;
        for (Product product : products2) {
            sb.append(product.getName()).append(", 1, ").append(product.getPrice()).append("\n");
            positionCount++;
        }

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sb.append(product.getName()).append(", ").append(quantity).append(", ")
                            .append(product.getPrice()).append("\n");
            positionCount++;
        }

        sb.append("Number of positions: ").append(positionCount);
        return sb.toString();
    }

}
