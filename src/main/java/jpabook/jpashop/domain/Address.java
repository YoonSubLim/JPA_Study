package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable // JPA 의 내장 타입이라 내장될 수 있음을 의미 // 사용하는 곳에서 Embedded.
// 둘 중 하나만 있어도 되지만, 둘 다 써줘도 된다.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
