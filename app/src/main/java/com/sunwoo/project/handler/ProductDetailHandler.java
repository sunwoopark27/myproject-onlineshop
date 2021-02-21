package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductDetailHandler extends AbstractProductHandler {

  public ProductDetailHandler(List<Product> productList) {
    super(productList);
    this.productList = productList;
  }

  @Override
  public void service() {
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
}

