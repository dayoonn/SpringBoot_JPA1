package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity //엔티티 클래스
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입
    private Address address;

    @OneToMany(mappedBy = "member") //order 테이블에 있는 member에 의해 매핑됨.
    private List<Order> orders = new ArrayList<>();

}
