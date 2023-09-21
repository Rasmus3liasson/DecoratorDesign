package Discounts;

import Product.BaseDiscount;
import Product.Product;
import Product.Discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FridayDiscount extends BaseDiscount {

    private final double percent = 0.10;

    public FridayDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        if (day == DayOfWeek.FRIDAY) {
            return true;
        }
        return false;
    }

    @Override
    protected double calculateDiscount(Product product) {
        double priceAfterDiscount = product.price() * percent;
        return priceAfterDiscount;
    }
}
