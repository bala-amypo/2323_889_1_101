// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDate;

// @Entity
// @Table(name = "consumption_logs")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class ConsumptionLog {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(optional = false)
//     private StockRecord stockRecord;

//     private Integer consumedQuantity;

//     private LocalDate consumedDate;
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_record_id")
    private StockRecord stockRecord;

    private Integer consumedQuantity;
    private LocalDate consumedDate;
}