package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductValidatorHandler extends AbstractProductHandler {

  @Override
  public void service() {}

  public ProductValidatorHandler(List<Product> productList) {
    super(productList);
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
}

