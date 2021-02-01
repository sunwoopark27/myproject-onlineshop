package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductHandler {

  public ProductList productList = new ProductList();

  public void add() {
    System.out.println("[상품 등록]");
    Product p = new Product();

    p.number = Prompt.inputInt("상품 번호: ");
    p.name = Prompt.inputString("상품 이름: ");
    p.price = Prompt.inputInt("상품 가격: ");

    productList.add(p);
    System.out.println();
  }

  public void list() {
    System.out.println("[상품 목록]");

    Product[] products = productList.toArray();

    for(Product p : products) {

      System.out.printf("번호: %d 이름: %s 가격: %d원\n", p.number, p.name, p.price);
      System.out.println("-----------------------------------------------------");

    }
  }

  public void detail() {
    System.out.println("[상품 상세보기]");

    Product product = productList.get(Prompt.inputInt("번호? "));

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

    Product product = productList.get(Prompt.inputInt("번호? "));
    if(product == null) {

      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",product.name));
      int price = Prompt.inputInt(String.format("가격(%s원)? ",product.price));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
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

    int no = Prompt.inputInt("번호? ");
    Product product = productList.get(no);
    if(product == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        productList.delete(no);

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

}

