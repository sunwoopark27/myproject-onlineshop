package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;

public abstract class AbstractProductHandler implements Command {

  protected List<Product> productList;

  public AbstractProductHandler(List<Product> productList) {
    this.productList = productList;
  }

  protected Product findByNo(int productNo) {
    Product[] list = productList.toArray(new Product[productList.size()]);
    for(Product p : list) {
      if(p.getNumber() == productNo) {
        return p;
      }
    }
    return null;
  }

  protected Product findByName(String productName) {
    Product[] list = productList.toArray(new Product[productList.size()]);
    for(Product p : list) {
      if(p.getName().equals(productName)) {
        return p;
      }
    }
    return null;
  }
}

