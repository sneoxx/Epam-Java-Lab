package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.BankService2;
import com.zaraev.epam.iface.GetAmountClientAccount;
import java.util.List;

public class BankServiceImpl2<T extends ClientAccount & GetAmountClientAccount<ClientAccount>> implements BankService2<T> {

  @Override
  public long getSum(List<T> accounts) {
    long sum = 0;

    for (T account : accounts) {
     //   account.additionalMethod();
        sum += account.getAmountClientAccountWithNotNull(account);
     // sum += account.getAmount();

    }
    return sum;
  }
}