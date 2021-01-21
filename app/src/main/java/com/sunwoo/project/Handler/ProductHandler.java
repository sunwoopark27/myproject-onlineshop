package com.sunwoo.project.Handler;

import com.sunwoo.util.Prompt;

public class ProductHandler {
  static class Product{
    int productNumber;
    String name;
    int price;
  }

  static final int LENGTH = 100;
  static Product[] products = new Product[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[상품 등록]");
    Product p = new Product();

    p.productNumber = Prompt.promptInt("상품 번호: ");
    p.name = Prompt.promptString("상품 이름: ");
    p.price = Prompt.promptInt("상품 가격: ");

    products[size++] = p;
    System.out.println();
  }
  public static void list() {
    System.out.println("[상품 목록]");
    for(int i = 0; i < size; i++) {
      Product p = products[i];
      System.out.printf("번호: %d 이름: %s 가격: %d원\n", p.productNumber, p.name, p.price);
      System.out.println("-----------------------------------------------------");
    }
  }
  static boolean exist(String name) {
    for(int i = 0; i < size; i++) {
      if(name.equals(products[i].name)) {
        return true;
      }
    }
    return false;
  }
}

