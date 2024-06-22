package uz.pdp.store_moc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.store_moc.entity.Category;
import uz.pdp.store_moc.entity.Income;
import uz.pdp.store_moc.entity.Product;
import uz.pdp.store_moc.interfaces.CategoryService;
import uz.pdp.store_moc.interfaces.IncomeService;
import uz.pdp.store_moc.interfaces.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final IncomeService incomeService;

    public AdminController(CategoryService categoryService, ProductService productService, IncomeService incomeService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.incomeService = incomeService;
    }




  /*  @GetMapping("/filterSales")
    public String filterSales(@RequestParam UUID productId,
                              @RequestParam UUID categoryId,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              Model model) {
        Product product = productService.findById(productId);
        LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        List<Sale> sales = saleService.filterByDate(product, start, end);
        model.addAttribute("sales", sales);

        return adminPage(null, null, categoryId, productId, model);
    }*/
}
