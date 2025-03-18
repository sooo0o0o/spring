package kr.co.ch07.entity.shop;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId")
    private Customer customer;

    private int orderPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

    @CreationTimestamp
    private LocalDateTime orderDate;


}
