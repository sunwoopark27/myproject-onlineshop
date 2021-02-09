package com.sunwoo.util;

public class Stack extends List implements Cloneable {

  public Object push(Object item) {
    this.add(item);
    return item;
  }

  public Object pop() {
    Object obj = this.delete(this.size - 1);
    return obj;
  }

  @Override
  public Stack clone() throws CloneNotSupportedException {

    Stack stack = new Stack();
    for (int i = 0; i < this.size; i++ ) {
      stack.push(this.get(i));
    }

    return stack;
  }
}
