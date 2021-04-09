package com.sunwoo.project.handler;

public class BoardChooseHandler {

  static BoardServiceProduct boardServiceProduct = new BoardServiceProduct();
  static BoardServiceReview boardServiceReview = new BoardServiceReview();
  static BoardServiceShipping boardServiceShipping = new BoardServiceShipping();
  static BoardServiceExchangeReturn boardServiceExchangeReturn = new BoardServiceExchangeReturn();

  public void chooseBoard() throws CloneNotSupportedException {
    while(true) {
      System.out.println("[메인 > 게시판]");
      System.out.println("1. 상품 문의");
      System.out.println("2. 배송 문의");
      System.out.println("3. 교환/반품 문의");
      System.out.println("4. 리뷰");
      System.out.println("0. 이전 메뉴");
      System.out.println();

      String command = com.sunwoo.util.Prompt.inputString("명령> ");
      System.out.println();

      switch(command) {
        case "1" :
          boardServiceProduct.menu("상품 문의");
          break;

        case "2" :
          boardServiceShipping.menu("배송 문의");
          break;

        case "3" :
          boardServiceExchangeReturn.menu("교환/반품 문의");
          break;

        case "4" :
          boardServiceReview.menu("리뷰");
          break;

        case "0" :
          System.out.println("메인으로 돌아갑니다.");
          System.out.println();
          return;

        default :
          System.out.println("잘못된 메뉴 번호 입니다.");

      }
      System.out.println();
    }
  }
}
