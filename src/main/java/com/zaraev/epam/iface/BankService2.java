package com.zaraev.epam.iface;

import java.util.List;

public interface BankService2<T> {

  long getSum(List<T> list);
//  void copy(List<? extends T> src, List<? super T> dest);
}
