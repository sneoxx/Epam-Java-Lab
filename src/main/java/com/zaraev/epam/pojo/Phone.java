package com.zaraev.epam.pojo;

public class Phone extends Product<Phone> {

  String model;

  @Override
  int subCompare(Phone p) {
    return 0;
  }
}
