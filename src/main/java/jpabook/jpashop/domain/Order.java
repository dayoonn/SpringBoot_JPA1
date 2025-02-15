package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders") //이 어노테이션을 적지 않으면 Order 테이블이 생성됨.(order은 DB에서 오류날 수 있는 테이블 명)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; //연관관계 주인

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) //order 필드에 의해 매핑됨
    private List<OrderItem> orderItems=new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //연관관계 주인

    //java 8이후로는 LocalDateTime를 사용하면 hibernate가 db의 date와 알아서 매핑해줌
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER,CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }
    //==생성 메서드==//
    public static Order createOrder(Member member,Delivery delivery, OrderItem... orderItems){
        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem  orderItem:orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); //주문 상태 변경
        order.setOrderDate(LocalDateTime.now()); //현재 시간
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문취소
     * */
    public void cancle(){
        if(delivery.getStaus()==DeliveryStatus.COMP){ //배송 완료 상태
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }
        this.setStatus(OrderStatus.CANCLE);
        for(OrderItem orderItem : orderItems){ //한번 주문할때 고객이 상품 2개를 주문한다면 각각의 상품도 cancle상태로 변경
            orderItem.cancle();
        }
    }
    //== 조회 로직 ==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalprice=0;
        for(OrderItem orderItem:orderItems){
            totalprice+=orderItem.getTotalPrice(); //
        }
        return totalprice;
    }

}
