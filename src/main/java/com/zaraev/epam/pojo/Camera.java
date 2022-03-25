package com.zaraev.epam.pojo;

public class Camera extends Product<Camera> {

//  @Override
//  public int compareTo(Product o) {
//    return 0;
//  }

  String megapixel;

//  @Override
//  public int compareTo(Camera o) {
//    return  1;
//  //  return super.compareTo((Product) this);
////  }
//  @Override
//  public int compareTo(Camera o) {
//    return 0;
//  }

  @Override
  int subCompare(Camera p) {
    return 1;
  }
}
