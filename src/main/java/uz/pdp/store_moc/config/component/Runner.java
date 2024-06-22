package uz.pdp.store_moc.config.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.store_moc.entity.Category;
import uz.pdp.store_moc.entity.Product;
import uz.pdp.store_moc.entity.Roles;
import uz.pdp.store_moc.entity.User;
import uz.pdp.store_moc.interfaces.CategoryService;
import uz.pdp.store_moc.interfaces.ProductService;
import uz.pdp.store_moc.interfaces.RoleService;
import uz.pdp.store_moc.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class Runner implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final ProductService productService;

    private final UserRepo userRepo;


    public Runner(PasswordEncoder passwordEncoder, RoleService roleService, CategoryService categoryService, ProductService productService, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userRepo = userRepo;
    }


    @Override
    public void run(String... args) throws Exception {
       // generateStudentTimeTable();
    }

    private void generateStudentTimeTable() {
        Category category = new Category();
        category.setName("Electronics");
        categoryService.save(category);

        Product product = new Product();
        product.setName("Iphone 13");
        product.setCategory(category);
        productService.save(product);

        Product product2 = new Product();
        product2.setName("Iphone 14");
        product2.setCategory(category);
        productService.save(product2);

        Product product3 = new Product();
        product3.setName("Iphone 15");
        product3.setCategory(category);
        productService.save(product3);

        Category category1 = new Category();
        category1.setName("Clothes");
        categoryService.save(category1);

        Product product4 = new Product();
        product4.setName("T.Shirt");
        product4.setCategory(category1);
        productService.save(product4);

        Product product5 = new Product();
        product5.setName("Jacket");
        product5.setCategory(category1);
        productService.save(product5);


        Category category2 = new Category();
        category2.setName("Books");
        categoryService.save(category2);

        Product product6 = new Product();
        product6.setName("Java");
        product6.setCategory(category2);
        productService.save(product6);

        Product product7 = new Product();
        product7.setName("Python");
        product7.setCategory(category2);
        productService.save(product7);

        Product product8 = new Product();
        product8.setName("C#");
        product8.setCategory(category2);
        productService.save(product8);

        Product product9 = new Product();
        product9.setName("Math");
        product9.setCategory(category2);
        productService.save(product9);

        Product product10 = new Product();
        product10.setName("Computer Science");
        product10.setCategory(category2);
        productService.save(product10);

        Category category4 = new Category();
        category4.setName("Foods");
        categoryService.save(category4);

        Product product11 = new Product();
        product11.setName("Banana");
        product11.setCategory(category4);
        productService.save(product11);

        Product product12 = new Product();
        product12.setName("Apple");
        product12.setCategory(category4);
        productService.save(product12);

        Product product13 = new Product();
        product13.setName("Orange");
        product13.setCategory(category4);
        productService.save(product13);

        Product product14 = new Product();
        product14.setName("Potato");
        product14.setCategory(category4);
        productService.save(product14);

        Product product15 = new Product();
        product15.setName("Carrot");
        product15.setCategory(category4);
        productService.save(product15);

        Product product16 = new Product();
        product16.setName("Cucumber");
        product16.setCategory(category4);
        productService.save(product16);

        Product product17 = new Product();
        product17.setName("Tomato");
        product17.setCategory(category4);
        productService.save(product17);

        Product product18 = new Product();
        product18.setName("Onion");
        product18.setCategory(category4);
        productService.save(product18);


        Product product20 = new Product();
        product20.setName("Cauliflower");
        product20.setCategory(category4);
        productService.save(product20);

      /*  Roles role = new Roles();
        role.setName("ROLE_ADMIN");
        roleService.save(role);
        Roles role2 = new Roles();
        role2.setName("ROLE_USER");
        roleService.save(role2);
        Roles role3 = new Roles();
        role3.setName("ROLE_MANAGER");
        roleService.save(role3);*/
    }
}
