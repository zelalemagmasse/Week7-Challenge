package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
 // List<Item> findAllTopTenByNameOfItem();
  //  List<Item> findFirst10ByNameOfItem();
    //List<Item> findFirst1();
    //List<Item> findTopByNameOfItem();

    Iterable<Item> findAllByNameOfItemContainingIgnoreCase(String s);
    Iterable<Item> findAllByTagsContainingIgnoreCase(String s);


}
