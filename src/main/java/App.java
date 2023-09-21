
import Discounts.FridayDiscount;
import Discounts.MilkDiscount;
import Discounts.QuantityDiscount;
import Product.Product;
import Product.Discount;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // Create a list of different products
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Milk", 100.0, 5));
        productList.add(new Product("Bread", 50.0, 2));
        productList.add(new Product("Cheese", 80.0, 2));


        Discount milkDiscount = new MilkDiscount(null);
        Discount fridayDiscount = new FridayDiscount(milkDiscount);
        Discount quantityDiscount = new QuantityDiscount(fridayDiscount);

        // Apply discount to products
        for (Product product : productList) {
            double discountedPrice = quantityDiscount.apply(product);


            System.out.println("Produkt: " + product.name());
            System.out.println("Pris: " + product.price());
      System.out.println("Pris Rabatt " + discountedPrice);
            System.out.println("Beskrivning: " + quantityDiscount.getDescription(product));
            System.out.println("----------------------------");
        }
    }
}
