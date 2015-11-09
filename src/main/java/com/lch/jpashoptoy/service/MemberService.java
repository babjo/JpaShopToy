package com.lch.jpashoptoy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	
	public Long join(Member member){
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member){
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty())
			throw new IllegalStateException("이미 존재하는 회원입니다.");
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

}
