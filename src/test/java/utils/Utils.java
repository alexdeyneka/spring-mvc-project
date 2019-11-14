package utils;

import com.mvc.service.entity.User;
import com.mvc.service.model.ProductDTO;

import java.util.Arrays;
import java.util.List;

public class Utils {

    private Utils() {
    }

    public static List<ProductDTO> getProductList() {
        return Arrays.asList(new ProductDTO(1, "hlib", 50, 20.1, null, null),
                new ProductDTO(2, "maslo", 120, 44.4, null, null),
                new ProductDTO(3, "ikra", 15, 255.8, null, null));
    }

    public static List<User> getUserList() {
        return Arrays.asList(new User(1, "Alex", "111"),
                new User(2, "Igor", "222"),
                new User(3, "Jack", "333"));
    }
}
