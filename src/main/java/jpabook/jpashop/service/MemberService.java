package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // read 작업이 많아, readOnly 기본을 전체적으로 적용
// @AllArgsConstructor
@RequiredArgsConstructor // final 있는 필드만 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;
    // 생성자 인젝션시 final 로 해주는 것이 좋다.

    // 생성자 인젝션
//    @Autowired // 생성자가 하나만 있는 경우에는 스프링이 자동으로 @Autowired 생성해준다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    // -> AllArgsConstructor : 필요한 필드로 생성자 생성
    // -> RequiredArgsConstructor : final 이 있는 필드만 생성자 생성 (권장)
    // 둘 중 하나로 대체

    // Setter 인젝션
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    // -> 나중에 다른 MemberRepository 로 변경될 가능성이 있다.

    // 회원 가입
    @Transactional(readOnly = false) // 기본 값이 false 라 @Transactional 만 써줘도 된다.
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
