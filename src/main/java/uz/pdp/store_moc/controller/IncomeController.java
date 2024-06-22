package uz.pdp.store_moc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.store_moc.entity.Income;
import uz.pdp.store_moc.entity.Product;
import uz.pdp.store_moc.entity.User;
import uz.pdp.store_moc.interfaces.IncomeService;
import uz.pdp.store_moc.interfaces.ProductService;
import uz.pdp.store_moc.repo.IncomeRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
public class IncomeController {

    private final ProductService productService;
    private final IncomeService incomeService;
    private final HttpSession httpSession;

    public IncomeController(ProductService productService, IncomeService incomeService, HttpSession httpSession) {
        this.productService = productService;
        this.incomeService = incomeService;
        this.httpSession = httpSession;
    }

    @GetMapping("/addIncome")
    public String showAddIncomeForm( Model model) {
        Product currentProduct = (Product) httpSession.getAttribute("currentProduct");
        if (currentProduct == null) {
            return "redirect:/";
        }
        Product product = productService.findById(currentProduct.getId());
        model.addAttribute("product", product);
        return "/admin/addIncome";
    }

    @PostMapping("/saveIncome")
    public String saveIncome(@RequestParam("companyName") String companyName,
                             @RequestParam("description") String description,
                             @RequestParam("incomePrice") double incomePrice,
                             @RequestParam("amount") int amount,
                             Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() instanceof String) {
            return "redirect:/login";
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return "redirect:/";
        }
        Product currentProduct = (Product) httpSession.getAttribute("currentProduct");
        if (currentProduct == null) {
            return "redirect:/";
        }

        Product product = productService.findById(currentProduct.getId());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(dateTimeFormatter);

        Income income = new Income();
        income.setProduct(product);
        income.setDate(LocalDateTime.parse(formattedDate, dateTimeFormatter));
        income.setCompanyName(companyName);
        income.setDescription(description);
        income.setIncomePrice(incomePrice);
        product.setRetailPrice(incomePrice * 1.7);
        income.setAmount(amount);
        income.setUser((User) auth.getPrincipal());
        incomeService.save(income);
        productService.save(product);
        return "redirect:/";
    }

}
