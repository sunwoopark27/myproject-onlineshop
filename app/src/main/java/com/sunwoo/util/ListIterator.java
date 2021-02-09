package com.sunwoo.util;

public class ListIterator extends AbstractIterator {

  List list;
  int cursor;

  public ListIterator(List list){
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return cursor < list.size();
  }

  @Override
  public Object next() {
    return list.get(cursor++);
  }
}
