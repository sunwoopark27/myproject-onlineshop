package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductUpdateHandler extends AbstractProductHandler{

  public ProductUpdateHandler(List<Product> productList) {
    super(productList);
    this.productList = productList;
  }

  @Override
  public void service() {
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
}

