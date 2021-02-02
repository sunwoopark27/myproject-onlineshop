package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Product;

public class ProductList {
  private Node first;
  private Node last;
  private int size = 0;


  public void add(Product p) {
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

  public Product[] toArray() {
    Product[] arr = new Product[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.product;
      cursor = cursor.next;
    }
    return arr;
  }

  public Product get(int productNo) {

    Node cursor = first;
    while(cursor != null) {
      if(cursor.product.getNumber() == productNo) {
        return cursor.product;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public void delete(int productNo) {
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

  public boolean exist(String name) {
    Node cursor = first;
    while(cursor != null) {
      if(name.equals(cursor.product.getName())) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  static class Node{
    Product product;
    Node next;
    Node prev;

    Node(Product p){
      this.product = p;
    }
  }
}
