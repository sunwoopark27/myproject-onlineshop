package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public abstract class AbstractOrderHandler implements Command {

  protected List<Order> orderList;

  public AbstractOrderHandler(List<Order> orderList){
    this.orderList = orderList;
  }

  public int inputOrderNumber(String promptTitle) {
    int number = -1;
    while(true) {
      number = Prompt.inputInt(promptTitle);
      if(number == -1) {
        return -1;
      }else if(findByNo(number) != null) {
        return number;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
  }

  protected Order findByNo(int orderNo) {
    Order[] list = orderList.toArray(new Order[orderList.size()]);
    for(Order o : list) {
      if(o.getNumber() == orderNo) {
        return o;
      }
    }
    return null;
  }


}