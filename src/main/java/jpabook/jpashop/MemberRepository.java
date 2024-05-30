package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext //스프링부트가 엔티티 매니저로 주입해주는 어노테이션
    private EntityManager em; //엔티티 매니저 (데이터베이스 통신, 엔티티 생명주기 관리)

    public Long save(Member member){
        em.persist(member);  // 엔티티를 영속 상태로 만들어 데이터베이스에 저장.
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class,id); //엔터터 조회 find(엔티티 클래스 , key) 존재 안하면 null 반환
    }



}
