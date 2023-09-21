package Discounts;

import Product.BaseDiscount;
import Product.Product;
import Product.Discount;

public class MilkDiscount extends BaseDiscount {

    private final double percent = 0.05;
    public MilkDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        if (product.name().toLowerCase().equals("milk")) {
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
