package com.sunwoo.project.handler;

import java.util.Iterator;
import java.util.List;
import com.sunwoo.project.domain.Product;

public class ProductListHandler extends AbstractProductHandler {

  public ProductListHandler(List<Product> productList) {
    super(productList);
    this.productList = productList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 목록]");

    Iterator<Product> iterator = productList.iterator();

    while (iterator.hasNext()) {
      Product p = iterator.next();
      System.out.printf("사진: %s\n이름: %s 가격: %d원\n",p.getPhoto(), p.getName(), p.getPrice());
      System.out.println("-----------------------------------------------------");

    }
  }
}

