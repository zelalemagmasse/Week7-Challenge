package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;

import java.util.Set;

@Service
public class BringItService {

	@Autowired
	ItemRepository itemRepository;

	public Model searchItem(Model model, String searchItem) {
		Set<Item> items = new HashSet<>();
		items.addAll(itemRepository.findAllByNameOfItemContainingIgnoreCase(searchItem));
		items.addAll(itemRepository.findAllByTagsContainingIgnoreCase(searchItem));
		model.addAttribute("items", items);
		return model;

	}
}
