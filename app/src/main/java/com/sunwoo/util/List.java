package com.sunwoo.util;

public class List {
  private Node first;
  private Node last;
  private int size = 0;

  public void add(Object o) {
    Node node = new Node(o);

    if(first == null) {
      first = node;
      last = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size++;

  }

  public Object[] toArray() {

    Object[] arr = new Object[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }

    return arr;
  }

  public Object get(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    Node cursor = first;
    int count = 0;
    while(cursor != null) {
      if(index == count++) {
        return cursor.obj;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean delete (Object obj) {

    Node cursor = first;
    while(cursor != null) {
      if(cursor.obj.equals(obj)) {
        this.size--;
        if(first == last) { //노드가 하나일 경우
          first = null;
          last = null;
        }else if(cursor == first){ //첫번째 노드일 경우
          first = cursor.next;
          cursor.prev = null;
        }else if(cursor == last) { //마지막 노드일경우
          cursor.prev.next = null;
          last = cursor.prev;
        }else{//중간에 다른 노드들
          cursor.prev.next = cursor.next;
          if(cursor.next !=null) {
            cursor.next.prev = cursor.prev;
          }
        }
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  public Object delete(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    Object deleted = null;
    Node cursor = first;
    int count = 0;
    while(cursor != null) {
      if(index == count++) {
        deleted = cursor.obj;
        this.size--;
        if(first == last) { //노드가 하나일 경우
          first = null;
          last = null;
        }else if(cursor == first){ //첫번째 노드일 경우
          first = cursor.next;
          cursor.prev = null;
        }else if(cursor == last) { //마지막 노드일경우
          cursor.prev.next = null;
          last = cursor.prev;
        }else{//중간에 다른 노드들
          cursor.prev.next = cursor.next;
          if(cursor.next !=null) {
            cursor.next.prev = cursor.prev;
          }
        }
        break;
      }
      cursor = cursor.next;
    }
    return deleted;
  }

  public int indexOf(Object obj) {
    Object[] list = this.toArray();
    for(int i = 0; i < list.length; i++) {
      if(list[i].equals(obj)) {
        return i;
      }
    }
    return -1;
  }

  static class Node{
    Object obj;
    Node next;
    Node prev;

    Node(Object o){
      this.obj = o;
    }
  }
}
