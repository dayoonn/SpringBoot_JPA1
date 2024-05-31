package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders") //이 어노테이션을 적지 않으면 Order 테이블이 생성됨.(order은 DB에서 오류날 수 있는 테이블 명)
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member; //연관관계 주인

    @OneToMany(mappedBy = "order") //order 필드에 의해 매핑됨
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //연관관계 주인

    //java 8이후로는 LocalDateTime를 사용하면 hibernate가 db의 date와 알아서 매핑해줌
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER,CANCEL]

}
