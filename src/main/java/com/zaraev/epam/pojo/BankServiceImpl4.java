package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.AdditionalMethod;
import com.zaraev.epam.iface.BankService2;
import com.zaraev.epam.iface.CopyMethod;
import com.zaraev.epam.iface.GetAmountClientAccount;
import java.util.ArrayList;
import java.util.List;

public class BankServiceImpl4<T extends ClientAccount & GetAmountClientAccount<ClientAccount> & AdditionalMethod> implements
    BankService2<T>, CopyMethod{

  @Override
  public <T> void copy(List<? extends T> src, List<? super T> dest) {

  }

  class Box<T> {// обозначение типа - T
    // переменная с типом T
    private T item;

    // переменная с типом T
    //private static T item1; //Параметризованные поля не могут быть статическими.

    public void putItem(T item) { //параметр метода типа T
      this.item = item;
    }
    public T getItem() { // возвращает объект типа T
      return item;
    }
  }


  @Override
  public long getSum(List<T> accounts) {
    long sum = 0;

    for (T account : accounts) {
      account.additionalMethod();
      sum += account.getAmountClientAccountWithNotNull(account);
    }
    List<? extends Number> example1 = new ArrayList<Integer>();
    example1.add(null);
    return sum;
  }

//  @Override
//    public void copy(List<? extends T> src, List<? super T> dest) {
//    for (T p : src) {
//      dest.add(p);
//    }
//  }
//
//
//    public void copy(List<? extends T> src, List<? super T> dest) {
//
//  }

}