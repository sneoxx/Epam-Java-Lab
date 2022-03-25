package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.AdditionalMethod;
import com.zaraev.epam.iface.CopyMethod;
import java.util.List;

public class ClientAccountVTBBank extends ClientAccount implements AdditionalMethod, CopyMethod {

  @Override
  public Integer getAmount() {
    return super.getAmount();
  }

  @Override
  public void setAmount(Integer amount) {
    super.setAmount(amount);
  }

  @Override
  public void additionalMethod() {
    System.out.println("Особая логика");
   }

  @Override
  public String toString() {
    return "ClientAccountVTBBank{" +
        "amount=" + amount +
        '}';
  }

  @Override
  public <T> void copy(List<? extends T> src, List<? super T> dest) {
    for (T p : src) {
     dest.add(p);
   }
  }

}
