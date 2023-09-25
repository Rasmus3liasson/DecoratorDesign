
import Discounts.FridayDiscount;

import Discounts.GeneralDiscounts.Applicability;
import Discounts.GeneralDiscounts.DiscountLogic;
import Discounts.GeneralDiscounts.GeneralDiscount;
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

        // VG assignment
        // Test for discount of 20 % and price is over 20 with an example class
        class GeneralDiscountExample {
            public static boolean isApplicable(Product product) {
                return product.price() >= 20;
            }

            public static double calculateDiscount(Product product) {
                if (product.price() >= 20) {
                    return product.price() * 0.20;
                }
                return 0.0;
            }
        }

        Applicability applicability = GeneralDiscountExample::isApplicable;
        DiscountLogic percentageLogic = GeneralDiscountExample::calculateDiscount;


        GeneralDiscount testCase = new GeneralDiscount(applicability, percentageLogic, null);


        // Create a list of products to apply the GeneralDiscount
        List<Product> discountList = new ArrayList<>();
        discountList.add(new Product("Produkt1", 25.0, 1));
        discountList.add(new Product("Produkt2", 30.0, 5));
        discountList.add(new Product("Produkt3", 15.0, 5));

        // Apply the GeneralDiscount to products in discountList
        for (Product product : discountList) {
            double discountedPrice = testCase.apply(product);
            System.out.println("Produkt: " + product.name());
            System.out.println("Pris: " + product.price());
            System.out.println("Pris Rabatt " + discountedPrice);
            System.out.println("Beskrivning: " + quantityDiscount.getDescription(product));
            System.out.println("----------------------------");
        }
    }
}
