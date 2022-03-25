package com.zaraev.epam;


import com.zaraev.epam.annotation.Hobby;
import com.zaraev.epam.annotation.MyDescriptionEnum;
import java.util.ArrayList;
import java.util.List;

@Hobby(kind="cinema") // нужно указать обязательные элементы
@Hobby(kind="photo", level = 3, tags={"travel", "nature"}, description = MyDescriptionEnum.TELEGRAM) // повторяемая аннотация
class NationalGeographicChannel extends UniversalChannel{

  @Override
  public void originalMethod() {
    System.out.println("Переопределенный метод");
  }

  @Deprecated
  public void oldMethod() {
    System.out.println("Устаревший метод");
  }

  @SuppressWarnings("rawtypes")
  public void methodWithRawTypes() {
    List lists = new ArrayList();
    System.out.println("Метод с сырыми типами");
  }

}