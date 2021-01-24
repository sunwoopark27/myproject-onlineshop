package com.sunwoo.project.Handler;

import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductHandler {

  static final int LENGTH = 100;
  Product[] products = new Product[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[상품 등록]");
    Product p = new Product();

    p.productNumber = Prompt.promptInt("상품 번호: ");
    p.name = Prompt.promptString("상품 이름: ");
    p.price = Prompt.promptInt("상품 가격: ");

    this.products[size++] = p;
    System.out.println();
  }
  public void list() {
    System.out.println("[상품 목록]");
    for(int i = 0; i < this.size; i++) {
      Product p = this.products[i];
      System.out.printf("번호: %d 이름: %s 가격: %d원\n", p.productNumber, p.name, p.price);
      System.out.println("-----------------------------------------------------");
    }
  }
  boolean exist(String name) {
    for(int i = 0; i < this.size; i++) {
      if(name.equals(this.products[i].name)) {
        return true;
      }
    }
    return false;
  }
}

