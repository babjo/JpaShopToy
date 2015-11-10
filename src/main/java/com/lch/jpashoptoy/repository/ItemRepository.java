package com.lch.jpashoptoy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lch.jpashoptoy.domain.Item;

@Repository
public class ItemRepository {
	
	@PersistenceContext EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

}
