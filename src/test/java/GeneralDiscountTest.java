import Discounts.GeneralDiscounts.Applicability;
import Discounts.GeneralDiscounts.DiscountLogic;
import Discounts.GeneralDiscounts.GeneralDiscount;
import Discounts.MilkDiscount;
import Product.Discount;
import Product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GeneralDiscountTest {

    @Test
    void testWithDifferentDiscounts() {

        Applicability price = p -> p.price() >= 200.0;
        DiscountLogic percentLogic = p -> p.price() * 0.10;

        // Milk discount to add to nextDiscount for combining discounts
        Discount milkDiscount = new MilkDiscount(null);
        GeneralDiscount generalDiscount = new GeneralDiscount(price, percentLogic, milkDiscount);

        Product productWithMilkDiscount = new Product("Milk ", 1200.0, 1);
        Product productWithOutMilkDiscount = new Product("Milkk ", 1200.0, 1);

        double priceWithDiscount = generalDiscount.apply(productWithMilkDiscount);
        double priceWithOutMilkDiscount = generalDiscount.apply(productWithOutMilkDiscount);

        // Applies combined discounts for the first, and the second only the generalDiscount
        assertThat(priceWithDiscount).isEqualTo(180.0);
        assertThat(priceWithOutMilkDiscount).isEqualTo(120.0);

    }

    @Test
    void testDiscountToMultipleProducts() {
        Applicability price = p -> p.price() >= 200.0;
        DiscountLogic percentLogic = p -> p.price() * 0.20;
        GeneralDiscount discount = new GeneralDiscount(price, percentLogic, null);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Tv", 500.0, 1));
        products.add(new Product("Handduk", 220.0, 2));
        products.add(new Product("Telefon", 100.0, 3));
        products.add(new Product("Dator", 200.0, 1));
        products.add(new Product("Köksbord", 50.0, 5));

        for (Product product : products) {
            double priceWithDiscount = discount.apply(product);
            if (product.name().equals("Telefon") || product.name().equals("Köksbord")) {
                // No discount should be applied because the product won't pass the Applicability
                assertThat(priceWithDiscount).isEqualTo(0.0);
            } else {
                assertThat(priceWithDiscount).isEqualTo(product.price() * 0.20);
            }
        }
    }

}
