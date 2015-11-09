package com.lch.jpashoptoy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lch.jpashoptoy.domain.Address;
import com.lch.jpashoptoy.domain.Member;
import com.lch.jpashoptoy.service.MemberService;


@Controller
public class MemberController {
	
    @Autowired MemberService memberService;
    
    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String create(Member member, String city, String street, String zipcode) {

        Address address = new Address(city, street, zipcode);
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String list(Model model){
    	List<Member> member = memberService.findMembers();
    	model.addAttribute("members", member);
 
    	return "members/memberList";
    }

}
