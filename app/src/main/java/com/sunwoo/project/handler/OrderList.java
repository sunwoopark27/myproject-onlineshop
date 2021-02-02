package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Order;

public class OrderList {
  private Node first;
  private Node last;
  private int size = 0;


  public void add(Order o) {
    Node node = new Node(o);

    if(last == null) {
      first = node;
      last = node;
    }else {
      node.prev = last;
      last.next = node;
      last = node;
    }

    this.size++;
  }

  public Order[] toArray() {

    Order[] arr = new Order[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.order;
      cursor = cursor.next;
    }

    return arr;
  }

  public Order get(int orderNo) {

    Node cursor = first;
    while(cursor != null) {
      if(orderNo == cursor.order.getNumber()) {
        return cursor.order;        
      }
      cursor = cursor.next;
    }
    return null;
  }

  public void delete(int orderNo) {

    Order order = get(orderNo);
    if(order == null) {
      return;
    }

    Node cursor = first;
    while(cursor != null) {
      if(cursor.order == order) {
        if(first == last) {
          first = null;
          last = null;
        }else if(cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        }else if(cursor == last) {
          cursor.prev.next = null;
          last = cursor.prev;
        }else {
          cursor.prev.next = cursor.next;
          if(cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        this.size--;
        break;
      }
      cursor = cursor.next;
    }
  }

  public boolean exist(int number){
    Node cursor = first;
    while(cursor != null) {
      if(number == cursor.order.getNumber()) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  static class Node{
    Order order;
    Node next;
    Node prev;

    Node(Order o){
      this.order = o;
    }
  }


}
