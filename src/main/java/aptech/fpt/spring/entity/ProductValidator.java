package aptech.fpt.spring.entity;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product st = (Product) o;
        if (st.getName().equals("Le Cam Quit")) {
            errors.rejectValue("name", null, "Riêng anh không thể đăng ký.");
        }
    }
}
