package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
   List<Item> findAllByNameOfItemContainingIgnoreCase(String s);
   List<Item> findAllByTagsContainingIgnoreCase(String s);
    @Query(value = "select top 10 * from Item", nativeQuery = true)
	Iterable<Item> getTop10();


}
