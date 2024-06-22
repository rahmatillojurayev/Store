package uz.pdp.store_moc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    private Product product;
    private Integer amount;
    private String customerName;
    private String description;
    private Double salePrice;
    @ManyToOne
    private User user;
    private LocalDateTime date;
}
