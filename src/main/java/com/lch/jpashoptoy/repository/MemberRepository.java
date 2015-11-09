package com.lch.jpashoptoy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lch.jpashoptoy.domain.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public void save(Member member){
		em.persist(member);
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

}
