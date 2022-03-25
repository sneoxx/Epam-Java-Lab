package com.zaraev.epam.iface;

import java.util.List;

public interface CopyMethod {

  <T> void copy(List<? extends T> src, List<? super T> dest);

}