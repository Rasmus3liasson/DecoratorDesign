package Product;

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
        if (isApplicable(product)) {
            return "Rabatten är på: " + calculateDiscount(product) + " kr";
        }  else if (nextDiscount != null) {
            return nextDiscount.getDescription(product);
        }
        return "Ingen rabatt finns tillgänglig";
    }
}