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

        Role r = new Role();
        r.setRole("USER");
        roleRepository.save(r);

        r = new Role();
        r.setRole("ADMIN");
        roleRepository.save(r);

        User u= new User();
        u.addRole(r);

        u.setUserName("yaa");
        u.setPassword("abc123");
        u.setEmail("zee@gmail.com");
        u.addRole(r);
        userRepository.save(u);
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", userName='" + userName + '\'' +
//                ", roleOfUsers=" + roleOfUsers +
//

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
