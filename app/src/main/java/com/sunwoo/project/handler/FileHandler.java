package com.sunwoo.project.handler;

import java.io.ObjectInputStream;
import java.util.List;

public class FileHandler {

  List<?> dataList;

  public <T> FileHandler(List<T> dataList) {
    this.dataList = dataList;
  }

  public void loadObject() {
    try(ObjectInputStream in = new ObjectInputStream()){

    } catch ()
  }

  public void saveObject() {


  }
}
