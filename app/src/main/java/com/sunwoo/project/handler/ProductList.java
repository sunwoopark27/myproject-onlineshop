package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Product;

public class ProductList {
  Node first;
  Node last;
  int size = 0;

  static class Node{
    Product product;
    Node next;
    Node prev;

    Node(Product p){
      this.product = p;
    }
  }

  void add(Product p) {
    Node node = new Node(p);

    if(last == null) {
      first = node;
      last = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size++;
  }

  Product[] toArray() {
    Product[] arr = new Product[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.product;
      cursor = cursor.next;
    }
    return arr;
  }

  Product get(int productNo) {

    Node cursor = first;
    while(cursor != null) {
      if(cursor.product.number == productNo) {
        return cursor.product;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int productNo) {
    Product product = get(productNo);

    if(product == null) {
      return;
    }

    Node cursor = first;
    while(cursor != null) {
      if(cursor.product == product) {
        if(first == last) {
          first = null;
          last = null;
        }else if(cursor == first) {
          first = cursor.next;
          first.prev = null;
        }else if(cursor == last) {
          last = cursor.prev;
          last.next = null;
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

  boolean exist(String name) {
    Node cursor = first;
    while(cursor != null) {
      if(name.equals(cursor.product.name)) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }
}
