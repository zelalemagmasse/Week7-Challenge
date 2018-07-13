package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BringItService {

    @Autowired
    ItemRepository itemRepository;

    public Model searchItem(Model model, String  searchItem) {


        if (itemRepository.findAllByNameOfItemContainingIgnoreCase(searchItem) != null) {
            model.addAttribute("items", itemRepository.findAllByNameOfItemContainingIgnoreCase(searchItem));
            return model;
        } else {

            model.addAttribute("items", itemRepository.findAllByTagsContainingIgnoreCase(searchItem));
            return model;
        }


    }
}
