package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.GetAmountClientAccount;

public class ClientAccount implements GetAmountClientAccount<ClientAccount> {

  Integer amount;

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
//
//  @Override
//  public <T> void copy(List<? extends T> src, List<? super T> dest) {
//    for (T p : src) {
//      dest.add(p);
//    }
//  }
}