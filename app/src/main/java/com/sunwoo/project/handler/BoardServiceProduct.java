package com.sunwoo.project.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sunwoo.project.App;
import com.sunwoo.project.domain.Board;

public class BoardServiceProduct {

  static List<Board> boardProductList = new ArrayList<>(); //상품 문의

  static File boardsOfProduct = new File("boardsOfProduct");

  public void menu(String choice) {

    loadObjects(boardsOfProduct);
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
            App.chooseBoard();
            break;

          default :
            Command commandHandler = commandMap.get(command);
            if(commandHandler == null) {
              System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              break;
            } else {
              commandHandler.service();
              break;
            }
        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      saveObjects(boardsOfProduct, boardProductList);
      System.out.println();
    }
  }

  @SuppressWarnings("unchecked")
  static void loadObjects(File file) {
    try(ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(file)))) {

      boardProductList = (List<Board>) in.readObject();
      System.out.println("상품 문의 로딩!");


    } catch (Exception e) {
      System.out.println("상품 문의 데이터 로딩 중 오류 발생!");
    }
  }

  static <T extends Serializable>void saveObjects(File file, List<T> dataList) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(file)))) { 

      out.writeObject(dataList);
      System.out.println("상품문의가 등록되었습니다.");

    } catch (Exception e) {
      System.out.println("상품문의 데이터 파일로 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

}