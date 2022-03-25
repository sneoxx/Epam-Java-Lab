package com.zaraev.epam.pojo;

public abstract class Product<T extends Product<T>> implements Comparable<T> {

  String name;
  Integer price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public int compareTo(T o) {
    return 0;
  }

  abstract int subCompare(T p);

}