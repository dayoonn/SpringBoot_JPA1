package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable //임베디드 내장 타입 포함
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
