package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String nameOfItem;


    public boolean isSoldout() {
        return Soldout;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nameOfItem='" + nameOfItem + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", price=" + price +
                ", Soldout=" + Soldout +
                '}';
    }

    public void setSoldout(boolean soldout) {
        Soldout = soldout;
    }

    private String description;
    private String tags;
    private long price;
    private boolean Soldout;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Item() {
    }



}
