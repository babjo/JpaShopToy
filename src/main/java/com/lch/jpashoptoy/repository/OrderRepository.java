package com.lch.jpashoptoy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lch.jpashoptoy.domain.Order;

@Repository
public class OrderRepository {
	
	@PersistenceContext EntityManager em;

	public List<Order> findAll() {
		return em.createQuery("select o from Order o", Order.class).getResultList();
	}

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

}
