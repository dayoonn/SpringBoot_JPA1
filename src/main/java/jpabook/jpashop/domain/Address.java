package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable //임베디드 내장 타입 포함
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {
    }
    public Address(String city, String street, String zipcode) { //생성자에서 값을 모두 초기화
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}