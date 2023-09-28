package Product;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDiscount implements Discount {
    protected abstract boolean isApplicable(Product product);

    protected abstract double calculateDiscount(Product product);

    protected Discount nextDiscount;

    public BaseDiscount(Discount nextDiscount) {
        this.nextDiscount = nextDiscount;
    }

    @Override
    public double apply(Product product) {
        double discount = isApplicable(product) ? calculateDiscount(product) : 0.0;
        if (nextDiscount != null) {
            discount += nextDiscount.apply(product);
        }
        return discount;
    }

    @Override
    public String getDescription(Product product) {
        List<String> appliedDiscounts = new ArrayList<>();

        if (isApplicable(product)) {
            appliedDiscounts.add(this.getClass().getSimpleName());
        }

        if (nextDiscount != null) {
            appliedDiscounts.add(nextDiscount.getDescription(product));
        }

        if (!appliedDiscounts.isEmpty()) {
            return String.join(", ", appliedDiscounts);
        }
        return "Ingen rabatt applicerades";
    }




}
