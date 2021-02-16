package com.sunwoo.util;

import java.lang.reflect.Array;

public class List<E> {
  private Node<E> first;
  private Node<E> last;
  protected int size = 0;

  public void add(E o) {
    Node<E> node = new Node<>(o);

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
    Node<E> cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }

    return arr;
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {

    if(arr.length < size) {
      // 파라미터로 받은 배열이 현재 저장된 항목의 크기보다 작으면
      // 새배열 만든다.
      arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), size);
    }

    Node<E> cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }

    return arr;
  }

  public E get(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    Node<E> cursor = first;
    int count = 0;
    while(cursor != null) {
      if(index == count++) {
        return cursor.obj;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean delete (E obj) {

    Node<E> cursor = first;
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

  public E delete(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    E deleted = null;
    Node<E> cursor = first;
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

  public int indexOf(E obj) {
    int index = 0;
    Node<E> cursor = first;

    while(cursor != null) {
      if(cursor.obj == obj) {
        return index;
      }
      cursor = cursor.next;
      index++;
    }
    return -1;
  }

  public int size() { //getter로 쓰지 않아 지금 java의 stack모방중! 
    return this.size;
  }

  static class Node<T>{
    T obj;
    Node<T> next;
    Node<T> prev;

    Node(T o){
      this.obj = o;
    }
  }

  public Iterator<E> iterator() throws CloneNotSupportedException {
    return new Iterator<E>() {

      int cursor;

      @Override
      public boolean hasNext() {
        return cursor < List.this.size();
      }

      @Override
      public E next() {
        return List.this.get(cursor++);
      }
    };
  }

}
