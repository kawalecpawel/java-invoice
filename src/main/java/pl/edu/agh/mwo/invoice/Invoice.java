package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private Collection<Product> products2 = new ArrayList<>();

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

    public BigDecimal getNetPrice() {

        BigDecimal netPrice = BigDecimal.ZERO;
        for (Product product : this.products2) {
            netPrice = netPrice.add(product.getPrice());
        }
        //--return netPrice;

        // must be uncommented to pass testInvoiceHasProperSubtotalForManyProducts
        //must be commented when code below is uncommented
    //--}

        //--igDecimal netPrice = BigDecimal.ZERO;
        //--for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            netPrice = netPrice.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return netPrice;

        //must be uncommented to pass test testInvoiceSubtotalWithTwoDifferentProducts
        //must be uncommented to pass test testInvoiceHasPropoerSubtotalWithQuantityMoreThanOne
        // must be commented when code above is uncommented
    }

    public BigDecimal getTax() {
        /*BigDecimal tax = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal taxOfProduct = product.getPrice().multiply(product.getTaxPercent()).multiply(BigDecimal.valueOf(quantity));
            tax = tax.add(taxOfProduct);
        }
        return tax;
    }*/
        //must be commented when below code is uncommented

        BigDecimal totalTax = BigDecimal.ZERO;

        for (Product product : products2) {
            BigDecimal tax = product.getPrice().multiply(product.getTaxPercent());
            totalTax = totalTax.add(tax);
        }

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {

            Product product = entry.getKey();
            int quantity = entry.getValue();
            //--BigDecimal tax = product.getPrice().multiply(product.getTaxPercent()).multiply(new BigDecimal(quantity));
            BigDecimal tax = product.getPrice().multiply(product.getTaxPercent()).multiply(BigDecimal.valueOf(quantity));
            totalTax = totalTax.add(tax);
        }

        return totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);
        //must be uncommented to pass test testInvoiceHasProperTaxValueForManyProduct
    }

    public BigDecimal getGrossPrice() {
        /*BigDecimal grossPrice = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            grossPrice = grossPrice.add(product.getPriceWithTax().multiply(BigDecimal.valueOf(quantity)));
        }
        return grossPrice;*/

        //return getNetPrice().add(getTax());
        //must be commented when code below is uncommented

        BigDecimal totalGrossPrice = BigDecimal.ZERO;


        for (Product product : products2) {
            BigDecimal grossPrice = product.getPrice().add(product.getPrice().multiply(product.getTaxPercent()));
            totalGrossPrice = totalGrossPrice.add(grossPrice);
        }


        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal grossPrice = product.getPrice().add(product.getPrice().multiply(product.getTaxPercent()));
            //--grossPrice = grossPrice.multiply(new BigDecimal(quantity));
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

    public BigDecimal getTotal() {
            return getGrossPrice();
        }
}

//must be uncommented to pass test testInvoiceHasProperTotalValueForManyProduct