package com.zaraev.epam.pojo;


import java.util.List;

public class HobbyPojo {

  public String typeOfContent;
  public int materialDifficultyLevel;
  public List<String> kindOfHobby;
  public String channelOwner;
  public String source;

  private String privateField;

  public <T> void fill(List<T> list, T val) {
    for (int i = 0; i < list.size(); i++)
      list.set(i, val);
  }

  public void setTypeOfContent(String typeOfContent) {
    this.typeOfContent = typeOfContent;
  }

  public void setMaterialDifficultyLevel(int materialDifficultyLevel) {
    this.materialDifficultyLevel = materialDifficultyLevel;
  }

  public void setKindOfHobby(List<String> kindOfHobby) {
    this.kindOfHobby = kindOfHobby;
  }

  public void setChannelOwner(String channelOwner) {
    this.channelOwner = channelOwner;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Override
  public String toString() {
    return "HobbyPojo{" +
        "typeOfContent='" + typeOfContent + '\'' +
        ", materialDifficultyLevel=" + materialDifficultyLevel +
        ", kindOfHobby=" + kindOfHobby +
        ", channelOwner='" + channelOwner + '\'' +
        ", source='" + source + '\'' +
        ", privateField='" + privateField + '\'' +
        '}';
  }
}