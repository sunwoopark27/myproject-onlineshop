package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Order;

public class OrderList {
  Node first;
  Node last;
  int size = 0;


  void add(Order o) {
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

  Order[] toArray() {

    Order[] arr = new Order[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.order;
      cursor = cursor.next;
    }

    return arr;
  }

  Order get(int orderNo) {

    Node cursor = first;
    while(cursor != null) {
      if(orderNo == cursor.order.number) {
        return cursor.order;        
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int orderNo) {

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
  static class Node{
    Order order;
    Node next;
    Node prev;

    Node(Order o){
      this.order = o;
    }
  }

  boolean exist(int number){
    Node cursor = first;
    while(cursor != null) {
      if(number == cursor.order.number) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

}
