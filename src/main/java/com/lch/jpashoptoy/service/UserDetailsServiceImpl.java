package com.lch.jpashoptoy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.repository.MemberRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	MemberRepository memberRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Member> members = memberRepository.findByName(username);
		if(members.isEmpty()) return null;
		return members.get(0); 
	}
}
