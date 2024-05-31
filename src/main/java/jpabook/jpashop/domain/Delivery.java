package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //반드시 스트링으로 사용. 오디너는 중간에 다른 값이 생성되면 예상치 못한 값이 조회 됨.
    private DeliveryStatus staus; //[READY,COMP]
}
