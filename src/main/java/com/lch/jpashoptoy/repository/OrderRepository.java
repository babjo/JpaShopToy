package com.lch.jpashoptoy.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.domain.Order;
import com.lch.jpashoptoy.domain.OrderSearch;
import com.lch.jpashoptoy.domain.q.QMember;
import com.lch.jpashoptoy.domain.q.QOrder;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class OrderRepository {
	
	@PersistenceContext EntityManager em;

	public List<Order> findAll(OrderSearch orderSearch) {
		QOrder qOrder = new QOrder("o");
		QMember qMemeber = new QMember("m");
		JPAQuery query = new JPAQuery(em).from(qOrder);
		
        if (orderSearch.getOrderStatus() != null) {
        	query.where(qOrder.status.eq(orderSearch.getOrderStatus()));
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
        	query.innerJoin(qOrder.member, qMemeber).where(qOrder.member.name.contains(orderSearch.getMemberName()));
        }
        
		return query.list(qOrder);
	}

	private List<Order> oldFindAll(OrderSearch orderSearch) {
		//쿼리 빌더
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//어떤 쿼리를 만들것인가
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        //from 문
        Root<Order> o = cq.from(Order.class);
        
        //where 절
        List<Predicate> criteria = new ArrayList<Predicate>();

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        
        //쿼리 생성
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대 검색 1000 건으로 제한
        return query.getResultList();
	}

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

}
