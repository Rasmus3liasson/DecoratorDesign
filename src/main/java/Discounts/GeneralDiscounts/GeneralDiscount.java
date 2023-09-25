package Discounts.GeneralDiscounts;


import Product.BaseDiscount;
import Product.Discount;
import Product.Product;


public class GeneralDiscount extends BaseDiscount {
    private final Applicability applicability;
    private final DiscountCalculator percentage;

    public GeneralDiscount(Applicability applicability, DiscountCalculator percentage, Discount nextDiscount) {
        super(nextDiscount);
        this.applicability = applicability;
        this.percentage = percentage;
    }

    @Override
    protected boolean isApplicable(Product product) {
        return applicability.isApplicable(product);
    }

    @Override
    protected double calculateDiscount(Product product) {
        return percentage.calculateDiscount(product);
    }
}
