package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.AdditionalMethod;
import com.zaraev.epam.iface.BankService2;
import com.zaraev.epam.iface.GetAmountClientAccount;
import java.util.List;

public class BankServiceImpl3<T extends ClientAccount & GetAmountClientAccount<ClientAccount> & AdditionalMethod> implements BankService2<T> {

  @Override
  public long getSum(List<T> accounts) {
    long sum = 0;

    for (T account : accounts) {
        account.additionalMethod();
        sum += account.getAmountClientAccountWithNotNull(account);
     // sum += account.getAmount();

    }
    return sum;
  }

//  public void copy(List<? extends T> src, List<? super T> dest) {
//    for (T p : src) {
//      dest.add(p);
//    }
//  }

}