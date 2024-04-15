package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity// jpa가 관리하는 엔티티가 됨.
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)// IDENTITY로 설정하여 DB가 알아서 ID값 생성하도록
    private Long id; // 데이터를 구분하기 위하여 시스템이 정하는 아이디
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
