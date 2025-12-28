// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;

// @Entity
// @Table(
//     name = "stock_records",
//     uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
// )
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class StockRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(optional = false)
//     private Product product;

//     @ManyToOne(optional = false)
//     private Warehouse warehouse;

//     private Integer currentQuantity;

//     private Integer reorderThreshold;

//     private LocalDateTime lastUpdated;
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private Integer currentQuantity;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
}