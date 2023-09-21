package Discounts;

import Product.BaseDiscount;
import Product.Product;
import Product.Discount;

public class QuantityDiscount extends BaseDiscount {

    private final double percent = 0.10;
    private final int quantity = 5;

    public QuantityDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return product.quantity() >= quantity;
    }

    @Override
    protected double calculateDiscount(Product product) {

        if (product.quantity() >= quantity) {
            return product.quantity() * product.price() * percent;
        }
        return 0.0;
    }
}
