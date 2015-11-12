package com.lch.jpashoptoy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lch.jpashoptoy.domain.Item;
import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.domain.Order;
import com.lch.jpashoptoy.service.ItemService;
import com.lch.jpashoptoy.service.MemberService;
import com.lch.jpashoptoy.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired OrderService orderService;
	@Autowired MemberService memberService;
	@Autowired ItemService itemService;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String createForm(Model model){
		List<Item> items = itemService.findItems();
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String create(Long memberId, Long itemId, int count){
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String list(Model model){
		List<Order> orders = orderService.findOrders();
		model.addAttribute("orders", orders);
		return "order/orderList"; 
	}

    @RequestMapping(value = "/orders/{orderId}/cancel")
    public String processCancelBuy(@PathVariable("orderId") Long orderId) {
    	
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
