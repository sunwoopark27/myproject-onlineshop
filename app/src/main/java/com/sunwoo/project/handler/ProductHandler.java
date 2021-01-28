package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductHandler {

  static final int LENGTH = 100;
  Product[] products = new Product[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[상품 등록]");
    Product p = new Product();

    p.number = Prompt.promptInt("상품 번호: ");
    p.name = Prompt.promptString("상품 이름: ");
    p.price = Prompt.promptInt("상품 가격: ");

    this.products[size++] = p;
    System.out.println();
  }
  public void list() {
    System.out.println("[상품 목록]");
    for(int i = 0; i < this.size; i++) {
      Product p = this.products[i];
      System.out.printf("번호: %d 이름: %s 가격: %d원\n", p.number, p.name, p.price);
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

  public void detail() {
    System.out.println("[상품 상세보기]");

    Product product = findByNo(Prompt.promptInt("번호? "));

    if (product == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("번호: %s ", product.number);
      System.out.printf("이름: %s\n", product.name);
      System.out.printf("가격: %s원\n", product.price);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[상품 수정하기]");

    Product product = findByNo(Prompt.promptInt("번호? "));
    if(product == null) {

      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.promptString(String.format("이름(%s)? ",product.name));
      int price = Prompt.promptInt(String.format("가격(%s원)? ",product.price));

      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        product.name = name;
        product.price = price;
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("[상품 삭제]");

    int index = indexOf(Prompt.promptInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        for(int x = index + 1; x < this.size; x++) {

          products[x - 1] = products[x];
        }
        this.products[--this.size] = null;
        System.out.println("상품 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("상품 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int productNo) {
    for(int i = 0; i < this.size; i++) {
      Product product = this.products[i];
      if(productNo == product.number) {
        return i;
      }
    }
    return -1;
  }

  Product findByNo(int productNo) {
    int i = indexOf(productNo);
    if(i == -1) {
      return null;
    }else {
      return this.products[i];
    }
  }
}

