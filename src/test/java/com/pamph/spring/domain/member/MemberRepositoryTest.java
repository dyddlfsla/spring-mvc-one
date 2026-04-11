package com.pamph.spring.domain.member;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    //given
    Member member = new Member("hello", 20);

    //when
    Member savedMember = memberRepository.save(member);

    //then
    Member foundMember = memberRepository.findById(savedMember.getId());
    Assertions.assertThat(foundMember).isEqualTo(savedMember);
  }

  @Test
  void findAll() {
    //given
    Member memberOne = new Member("member1", 20);
    Member memberTwo = new Member("member2", 30);

    memberRepository.save(memberOne);
    memberRepository.save(memberTwo);

    //when
    List<Member> foundMembers = memberRepository.findAll();

    //then
    Assertions.assertThat(foundMembers.size()).isEqualTo(2);
    Assertions.assertThat(foundMembers).containsExactly(memberOne, memberTwo);

  }
}