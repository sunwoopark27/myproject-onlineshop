package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {

  public ProductAddHandler(List<Product> productList) {
    super(productList);
    this.productList = productList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 등록]");
    Product p = new Product();

    p.setNumber(Prompt.inputInt("상품 번호: "));
    p.setName(Prompt.inputString("상품 이름: "));
    p.setPrice(Prompt.inputInt("상품 가격: "));
    p.setPhoto(Prompt.inputString("상품 사진: "));

    productList.add(p);
    System.out.println();
  }

}

