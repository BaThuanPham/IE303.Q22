import controller.ProductController;
import model.Product;
import view.MainView;

import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
            new Product("Product 1", 160, "images/img1.png"),
            new Product("Product 2", 200, "images/img2.png"),
            new Product("Product 3", 300, "images/img3.png"),
            new Product("Product 4", 400, "images/img4.png"),
            new Product("Product 5", 500, "images/img5.png"),
            new Product("Product 6", 600, "images/img6.png")
        );

        MainView view = new MainView();
        ProductController controller = new ProductController(view, products);

        controller.init();
        view.setVisible(true);
    }
}