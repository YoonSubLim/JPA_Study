package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Entity 임을 명시
@Getter @Setter // Lombok
public class Member {

    @Id // PK
    @GeneratedValue
    @Column(name = "member_id") // 지정해주지 않으면 column 이름이 그냥 id가 된다.
    private Long id;

    private String name;

    @Embedded // Embeddable 한 Address 를 사용한다.
    private Address address;

    // 하나의 멤버에 대해 여러 주문
    @OneToMany(mappedBy = "member") // 연관 관계의 주인은 member 입니다 의미이다.
    private List<Order> orders = new ArrayList<>();
    // 질문 : 위의 어노테이션만 보고 어떻게 어느 도메인의 member 에 매핑되는지 아나요 ?
    // 만약 여러 Entity 에서 FK 로 member_id 를 참조한다면.

}
