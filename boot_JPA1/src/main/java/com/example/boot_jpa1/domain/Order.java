package com.example.boot_jpa1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    // fetch = FetchType.LAZY : 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY)
    // 테이블간의 연관관계를 설정할 때 일대다(1:N)이면 @JoinColumn을 설정해줍니다.
    // 외래키를 매핑할때 사용
    // name 속성에는 매핑할 외래 키 컬럼명(이름)을 지정합니다.
    @JoinColumn(name = "member_id")
    private Member member; // 주문회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // cascaade : 영속성 전이
    // → 부모 엔티티가 영속화될 때 자식 엔티티도 같이 영속화되고 부모 엔티티가
    // 삭제될 때 자식 엔티티도 삭제되는 등 특정 엔티티를 영속 상태로 만들 때 연관된
    // 엔티티도 함께 영속 상태로 전이되는 것을 의미
    // 즉, 특정 엔티티에 대해 특정한 작업을 수행하면 관련된 엔티티에도 동일한 작업을 수행
    /*
    *   CascadeType.ALL : 모든 cascade를 적용
    *   CascadeType.PERSIST : 엔티티를 영속화할 때, 연관된 엔티티도 함께 유지
    *   CascadeType.MERGE : 엔티티 상태를 병합(Merge)할 때, 연관된 엔티티도 모두 병합
    *   CascadeType.REMOVE : 엔티티를 제거할 때, 연관된 엔티티도 모두 제거
    *   CascadeType.DETACH : 부모 엔티티를 detach() 수행하면, 연관 엔티티도
    *                        detach() 상태가 되어 변경 사항 반영x
    *   CascadeType.REFRESH : 상위 엔티티를 새로고침할 때, 연관된 엔티티도 모두 새로고침
    * */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // 배송 정보

    private LocalDateTime orderDate; // 주문시간

    // enum의 값을 index가 아닌 텍스트 값 그대로 저장하고 싶을 때
    // 사용하면 DB에 enum의 값 자체가 텍스트 그대로 저장이 잘 된다.
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    // -- 연관관계 메서드 --
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
