package com.lch.jpashoptoy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lch.jpashoptoy.domain.Item;
import com.lch.jpashoptoy.repository.ItemRepository;
import com.lch.jpashoptoy.repository.MemberRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired ItemRepository itemRepository;
	@Autowired MemberRepository memberRepository;

	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}

}
