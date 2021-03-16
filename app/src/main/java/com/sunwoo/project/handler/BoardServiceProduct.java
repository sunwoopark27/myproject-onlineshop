package com.sunwoo.project.handler;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.sunwoo.project.domain.Board;

public class BoardServiceProduct {

  static List<Board> boardProductList = new ArrayList<>(); //상품 문의

  public void menu(String choice) {

    loadBoards();
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardAddHandler(boardProductList));
    commandMap.put("2", new BoardListHandler(boardProductList));
    commandMap.put("3", new BoardDetailHandler(boardProductList));
    commandMap.put("4", new BoardUpdateHandler(boardProductList));
    commandMap.put("5", new BoardDeleteHandler(boardProductList));

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
          case "0" :
            System.out.println("게시판으로 돌아갑니다.");
            System.out.println();
            return;
          default :
            Command commandHandler = commandMap.get(command);
            if(commandHandler == null) {
              System.out.println("실행할 수 없는 메뉴 번호 입니다.");
            } else {
              commandHandler.service();
            }
        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      saveBoards();
      System.out.println();
    }
  }

  static void loadBoards() {
    try(Scanner in = new Scanner(new FileReader("boardsOfProduct.data"))) {
      while (true) {
        try {
          String record = in.nextLine();
          String[] fields = record.split(",");
          Board b = new Board();
          b.setNumber(Integer.parseInt(fields[0]));
          b.setTitle(fields[1]);
          b.setContent(fields[2]);
          b.setWriter(fields[3]);
          b.setRegisteredDate(Date.valueOf(fields[4]));
          b.setViewCount(Integer.parseInt(fields[5]));

          boardProductList.add(b);
          System.out.println("상품 문의 로딩!");
        }catch (NoSuchElementException e) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("상품 문의 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveBoards() {
    try (FileWriter out = new FileWriter("boardsOfProduct.data")) { 

      for (Board b : boardProductList) { // 번호 제목 내용 글쓴이 등록일 조회수(CRLF)
        out.write(String.format("%d,%s,%s,%s,%s,%d\n",
            b.getNumber(),
            b.getTitle(),
            b.getContent(),
            b.getWriter(),
            b.getRegisteredDate(),
            b.getViewCount()));
      }
      System.out.println("상품문의가 등록되었습니다.");

    } catch (Exception e) {
      System.out.println("상품문의 데이터 파일로 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

}