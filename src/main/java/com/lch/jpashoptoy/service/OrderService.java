package com.lch.jpashoptoy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lch.jpashoptoy.domain.Delivery;
import com.lch.jpashoptoy.domain.Item;
import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.domain.Order;
import com.lch.jpashoptoy.domain.OrderItem;
import com.lch.jpashoptoy.repository.ItemRepository;
import com.lch.jpashoptoy.repository.MemberRepository;
import com.lch.jpashoptoy.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired OrderRepository orderRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired ItemRepository itemRepository;

	public List<Order> findOrders() {
		return orderRepository.findAll();
	}

	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		Delivery delivery = new Delivery(member.getAddress());
		
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		Order order = Order.createOrder(member, delivery, orderItem);
		
		orderRepository.save(order);
		return order.getId();
	}
	
	

}
