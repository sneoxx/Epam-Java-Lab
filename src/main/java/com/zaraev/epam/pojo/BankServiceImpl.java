package com.zaraev.epam.pojo;

import com.zaraev.epam.iface.BankService;
import java.util.List;

public class BankServiceImpl implements BankService {

  public long getSum(List clientAccounts) { //метод вычисления суммы сбережений на счетах клиентов
    long sum = 0;
    for (Object o : clientAccounts) {
      if (o instanceof ClientAccount) {
        sum += ((ClientAccount) o).getAmount();
        System.out.println("Вынуждены использовать приведение типов");
      } else {
        System.out.println(
            "Без instanceof проверки получили бы ClassCastException в Runtime при загрязнении кучи");
      }
    }
    return sum;
  }

  public static long getSum1(List clientAccounts) { //метод вычисления суммы сбережений на счетах клиентов
    long sum = 0;
    for (Object o : clientAccounts) {
      if (o instanceof ClientAccount) {
        sum += ((ClientAccount) o).getAmount();
      }
    }
    return sum;
  }

}