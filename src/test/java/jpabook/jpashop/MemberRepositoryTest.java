package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class) //JUnit에게 스프링과 관련된 걸로 테스트 한다는 것을 알림
@SpringBootTest //스프링부트로 테스트 돌림
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository; //MemberRepository 인젝션 받음

    @Test //org.junit.jupiter.api.Test 사용
    @Transactional //테스트 시작 전에 트랜잭션을 시작하고, 테 스트 완료 후에 항상 롤백
    @Rollback(value = false)
    void testMember() throws Exception {
        //given
        Member member=new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); //두 객체의 ID 값을 비교하고, 그 값들이 같은지 검증
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

    }


}