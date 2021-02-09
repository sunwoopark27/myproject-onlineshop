package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Product;
import com.sunwoo.util.List;
import com.sunwoo.util.ListIterator;
import com.sunwoo.util.Prompt;

public class ProductHandler {

  private List productList = new List();

  public List getProductList(List productList) {
    return this.productList;
  }

  public void service() {

    loop:
      while(true) {
        System.out.println("[메인 > 상품]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.sunwoo.util.Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {
          case "1" :
            this.add();
            break;
          case "2" :
            this.list();
            break;
          case "3" :
            this.detail();
            break;
          case "4" :
            this.update();
            break;
          case "5" :
            this.delete();
            break;
          case "0" :
            System.out.println("메인으로 돌아갑니다.");
            System.out.println();
            break loop;
          default :
            System.out.println("잘못된 메뉴 번호 입니다.");
            System.out.println();

        }
        System.out.println();
      }
  }

  public void add() {
    System.out.println("[메인 > 상품 > 등록]");
    Product p = new Product();

    p.setNumber(Prompt.inputInt("상품 번호: "));
    p.setName(Prompt.inputString("상품 이름: "));
    p.setPrice(Prompt.inputInt("상품 가격: "));
    p.setPhoto(Prompt.inputString("상품 사진: "));

    productList.add(p);
    System.out.println();
  }

  public void list() {
    System.out.println("[메인 > 상품 > 목록]");

    ListIterator iterator = new ListIterator(this.productList);

    while (iterator.hasNext()) {
      Product p = (Product)iterator.next();
      System.out.printf("사진: %s\n이름: %s 가격: %d원\n",p.getPhoto(), p.getName(), p.getPrice());
      System.out.println("-----------------------------------------------------");

    }
  }

  public void detail() {
    System.out.println("메인 > 상품 > 상세 보기]");

    Product product = findByNo(Prompt.inputInt("번호? "));

    if (product == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("번호: %s ", product.getNumber());
      System.out.printf("이름: %s\n", product.getName());
      System.out.printf("사진: %s\n", product.getPhoto());
      System.out.printf("가격: %s원\n", product.getPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[메인 > 상품 > 수정]");

    Product product = findByNo(Prompt.inputInt("번호? "));
    if(product == null) {

      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",product.getName()));
      String photo = Prompt.inputString(String.format("사진(%s)? ",product.getPhoto()));
      int price = Prompt.inputInt(String.format("가격(%s원)? ",product.getPrice()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        product.setName(name);
        product.setPhoto(photo);
        product.setPrice(price);
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
    System.out.println("[메인 > 상품 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Product product = findByNo(no);
    if(product == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        productList.delete(product);

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

  public String inputProducts(String promptTitle) {
    String products = "";
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.isEmpty()) {
        return products;
      }
      if(findByName(name) != null) {
        if(products.length() != 0) {
          products += ", ";
        }
        products += name;
      }else {
        System.out.println("등록된 상품이 아닙니다.");
      }
    }
  }

  private Product findByNo(int productNo) {
    Object[] list = productList.toArray();
    for(Object obj : list) {
      Product p = (Product)obj;
      if(p.getNumber() == productNo) {
        return p;
      }
    }
    return null;
  }

  private Product findByName(String productName) {
    Object[] list = productList.toArray();
    for(Object obj : list) {
      Product p = (Product)obj;
      if(p.getName().equals(productName)) {
        return p;
      }
    }
    return null;
  }
}

