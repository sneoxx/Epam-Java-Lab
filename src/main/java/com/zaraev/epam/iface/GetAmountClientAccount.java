package com.zaraev.epam.iface;


import com.zaraev.epam.pojo.ClientAccount;

public interface GetAmountClientAccount<T extends ClientAccount> {

  default long getAmountClientAccountWithNotNull(T clientAccount) {
    return clientAccount.getAmount() == null ? 0 : clientAccount.getAmount();
  }

}