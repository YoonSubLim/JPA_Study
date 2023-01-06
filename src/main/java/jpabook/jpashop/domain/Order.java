package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // ORDER BY 예약어 문제로, 테이블 명을 바꾸어준다.
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // Order 와 Member 는 다대일 // 여러 주문에 대해 한 멤버
    @JoinColumn(name = "member_id") // 매핑 Column. member_id 가 FK 이다. // Many 쪽이 FK 가진다.
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [Order, Cancle]


}