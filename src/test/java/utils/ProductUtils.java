package utils;

import com.mvc.service.entity.User;
import com.mvc.service.model.ProductDTO;

public class ProductUtils {

    private ProductUtils() {
    }

    public static User getUserOne() {
        User user = new User();
        user.setId(1);
        user.setUserName("Alex");
        user.setPassword("111");
        return user;
    }

    public static User getUserTwo() {
        User user = new User();
        user.setId(2);
        user.setUserName("Igor");
        user.setPassword("222");
        return user;
    }

    public static User getUserThree() {
        User user = new User();
        user.setId(3);
        user.setUserName("Jack");
        user.setPassword("333");
        return user;
    }
}
