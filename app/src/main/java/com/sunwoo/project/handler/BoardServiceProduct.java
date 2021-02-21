package com.sunwoo.project.handler;

import java.util.ArrayList;
import com.sunwoo.project.App;
import com.sunwoo.project.domain.Board;

public class BoardServiceProduct {

  static ArrayList<Board> boardProductList = new ArrayList<>(); //상품 문의

  BoardAddHandler boardAddHandler = new BoardAddHandler(boardProductList);
  BoardListHandler boardListHandler = new BoardListHandler(boardProductList);
  BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardProductList);
  BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardProductList);
  BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardProductList);

  public void menu(String choice) throws CloneNotSupportedException {

    while(true) {
      System.out.printf("[메인 > 게시판 > %s]\n", choice);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 상세 보기");
      System.out.println("4. 수정");
      System.out.println("5. 삭제");
      System.out.println("0. 이전 메뉴");
      System.out.println();

      String command = com.sunwoo.util.Prompt.inputString("명령> ");
      System.out.println();
      try {
        switch(command) {
          case "1" :
            boardAddHandler.service();
            break;
          case "2" :
            boardListHandler.service();
            break;
          case "3" :
            boardDetailHandler.service();
            break;
          case "4" :
            boardUpdateHandler.service();
            break;
          case "5" :
            boardDeleteHandler.service();
            break;
          case "0" :
            System.out.println("게시판으로 돌아갑니다.");
            System.out.println();
            App.chooseBoard();
          default :
            System.out.println("잘못된 메뉴 번호 입니다.");
            System.out.println();

        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      System.out.println();
    }
  }

}