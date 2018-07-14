package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Loader  implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;



    @Override
    public  void run(String ... Strings)throws Exception{

        Role r2 = new Role();
        r2.setRole("USER");
        roleRepository.save(r2);
        Role r = new Role();
        r = new Role();
        r.setRole("ADMIN");
        roleRepository.save(r);

        User u= new User();
        u.addRole(r);

        u.setUserName("admin");
        u.setPassword("abc123");
        u.setEmail("zee@gmail.com");
        u.addRole(r);
        userRepository.save(u);
        
        
        User u2= new User();
        u2.setUserName("zee");
        u2.setPassword("zee");
        u2.setEmail("zele@gmail.com");
        u2.addRole(r2);
        userRepository.save(u2);

        Item i= new Item();
        i.setNameOfItem("Television");
        i.setDescription("Sumsung TV");
        i.setPrice(500);
        i.setTags("house hold");
        i.setSoldout(false);

        itemRepository.save(i);

        i= new Item();
        i.setNameOfItem("Bed");
        i.setDescription("King Size");
        i.setPrice(1200);
        i.setTags("house hold");
        i.setSoldout(false);

        itemRepository.save(i);

        i= new Item();
        i.setNameOfItem("Car");
        i.setDescription("Honda ,2018 Model");
        i.setPrice(5500);
        i.setTags("vehicle");
        i.setSoldout(false);

        itemRepository.save(i);

        i= new Item();
        i.setNameOfItem("Laptop");
        i.setDescription("Mac Laptop,1tb HDD,14inch ");
        i.setPrice(1200);
        i.setTags("computer");
        i.setSoldout(false);

        itemRepository.save(i);


    }

}
