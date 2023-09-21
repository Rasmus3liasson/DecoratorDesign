package Product;



public interface Discount {


    double apply(Product product);

    String getDescription(Product product);

}
