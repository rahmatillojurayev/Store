package uz.pdp.store_moc.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.store_moc.entity.*;
import uz.pdp.store_moc.interfaces.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final RoleService roleService;
    private final IncomeService incomeService;
    private final SaleService saleService;

    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @GetMapping("/login")
    public String home(Model model) {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username, @RequestParam String password, Model model) {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                model.addAttribute("error", "User already exists");
                return "login";
            }
        }
        Roles role = roleService.findByName("ROLE_USER");
        User user = new User();
        user.setUsername(username);
        user.setActive(true);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        model.addAttribute("error", "User created successfully");
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    // Directory to save uploaded images
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";


    @GetMapping("/")
    public String homePage(@RequestParam(name = "categoryId", required = false) UUID categoryId,
                           @RequestParam(name = "productId", required = false) UUID productId,
                           @RequestParam(name = "searchCategory", required = false) String searchCategory,
                           @RequestParam(name = "searchProduct", required = false) String searchProduct,
                           @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                           @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                           @RequestParam(name = "all", required = false) String all,
                           Model model) {

        List<Category> categories = categoryService.findAll();
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atStartOfDay().plusDays(1).minusSeconds(1) : null;

        model.addAttribute("categories", categories);

        if (searchCategory != null) {
            List<Category> searchCategories = categoryService.searchCategoriesByName(searchCategory);
            model.addAttribute("categories", searchCategories);
        }

        Category currentCategory = null;
        if (all != null) {
            httpSession.removeAttribute("currentCategory");
        } else {
            if (categoryId != null) {
                currentCategory = categoryService.findById(categoryId);
                httpSession.setAttribute("currentCategory", currentCategory);
            } else {
                currentCategory = (Category) httpSession.getAttribute("currentCategory");
            }
        }
        model.addAttribute("currentCategory", currentCategory);

        List<Product> products;
        if (currentCategory != null) {
            products = productService.findAllByCategoryId(currentCategory.getId());
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);

        if (searchProduct != null) {
            if (currentCategory != null) {
                products = productService.searchAllByName(searchProduct, currentCategory.getId());
            } else {
                products = productService.searchAllByName(searchProduct, null);
            }
            model.addAttribute("products", products);
        }

        Product currentProduct = null;
        if (productId != null) {
            currentProduct = productService.findById(productId);
            httpSession.setAttribute("currentProduct", currentProduct);
        } else {
            currentProduct = (Product) httpSession.getAttribute("currentProduct");
        }
        model.addAttribute("currentProduct", currentProduct);

        if (currentProduct != null) {
            List<Income> incomes = incomeService.findAllByProductId(currentProduct.getId());
            List<Sale> sales = saleService.findAllByProductId(currentProduct.getId());

            if (startDateTime != null && endDateTime != null) {
                incomes = incomeService.findAllByProductIdAndDateBetween(currentProduct.getId(), startDateTime, endDateTime);
                sales = saleService.findAllByProductIdAndDateBetween(currentProduct.getId(), startDateTime, endDateTime);
            }

            model.addAttribute("incomes", incomes);
            model.addAttribute("sales", sales);
        } else {
            List<Income> incomes = new ArrayList<>();
            List<Sale> sales = new ArrayList<>();

            if (startDateTime != null && endDateTime != null) {
                incomes = incomeService.findAllByProductIdAndDateBetween(null, startDateTime, endDateTime);
                sales = saleService.findAllByProductIdAndDateBetween(null, startDateTime, endDateTime);
            }

            model.addAttribute("incomes", incomes);
            model.addAttribute("sales", sales);
        }

        return "admin/admin";
    }



    @GetMapping("/sales")
    public String salesPage() {
        return "admin/sales";
    }
 /* @GetMapping("/income")
    public String incomePage(@RequestParam(name = "categoryId", required = false) UUID categoryId,
                             @RequestParam(name = "productId", required = false) UUID productId,
                             @RequestParam(name = "searchCategory", required = false) String searchCategory,
                             @RequestParam(name = "searchProduct", required = false) String searchProduct,
                             Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            httpSession.setAttribute("currentCategory", category);
            model.addAttribute("currentCategory", category);
            List<Product> products = productService.findAllByCategoryId(categoryId);
            model.addAttribute("products", products);
        }

        if (productId != null) {
            Product product = productService.findById(productId);
            httpSession.setAttribute("currentProduct", product);
            model.addAttribute("currentProduct", product);
        } else {
            Product currentProduct = (Product) httpSession.getAttribute("currentProduct");
            if (currentProduct != null) {
                Product product = productService.findById(currentProduct.getId());
                model.addAttribute("currentProduct", product);
            }
        }

        if (searchCategory != null) {
            List<Category> searchCategories = categoryService.searchCategoriesByName(searchCategory);
            model.addAttribute("categories", searchCategories);
        }

        if (searchProduct != null) {
            List<Product> searchProducts = productService.searchAllByName(searchProduct, categoryId);
            model.addAttribute("products", searchProducts);
        }

        return "admin/income";
    }*/
 @PostMapping("/uploadImage")
 public String uploadImage(@RequestParam("image") MultipartFile file,
                           @RequestParam UUID productId,
                           RedirectAttributes redirectAttributes) {
     if (file.isEmpty()) {
         redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
         return "redirect:/?productId=" + productId;
     }

     try {
         byte[] bytes = file.getBytes();
         Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
         Files.write(path, bytes);

         Product product = productService.findById(productId);
         product.setImage(bytes);
         productService.save(product);

         redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

     } catch (IOException e) {
         e.printStackTrace();
     }

     return "redirect:/?productId=" + productId;
 }
}

