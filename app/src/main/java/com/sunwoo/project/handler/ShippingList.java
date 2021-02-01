package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Shipping;

public class ShippingList {

  Node first;
  Node last;
  int size = 0;

  void add(Shipping s) {
    Node node = new Node(s);

    if(last == null) {
      first = node;
      last = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }
    size++;

  }

  Shipping[] toArray() {
    Shipping[] arr = new Shipping[size];
    Node cursor = first;
    int i = 0;

    while(cursor != null) {
      arr[i++] = cursor.shipping;
      cursor = cursor.next;
    }
    return arr;
  }

  Shipping get(int shippingNo) {
    Node cursor = first;
    while(cursor != null) {
      if(cursor.shipping.number == shippingNo) {
        return cursor.shipping;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int shippingNo) {

    Shipping shipping = get(shippingNo);

    if(shipping == null) {

      return;
    }

    Node cursor = first;
    while(cursor != null) {
      if(cursor.shipping == shipping) {
        if(first == last) {
          first = null;
          last = null;
        }else if(cursor == first) {
          first = cursor.next;
          cursor.prev = null; 
        }else if(cursor == last) {
          last = cursor.prev;
          last.next = null;
        }else {
          cursor.prev.next = cursor.next;
          if(cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        this.size --;
        break;
      }
      cursor = cursor.next;
    }
  }

  static class Node{
    Shipping shipping;
    Node next;
    Node prev;

    Node(Shipping s){
      this.shipping = s;
    }
  }
}
