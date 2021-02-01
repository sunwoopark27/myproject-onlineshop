package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.Prompt;

public class BoardHandler {

  BoardList boardList = new BoardList();

  public void add() {
    System.out.println("[새 게시글]");

    Board b = new Board();

    b.number = Prompt.inputInt("번호: ");
    b.title = Prompt.inputString("제목: ");
    b.content = Prompt.inputString("내용: ");
    b.writer = Prompt.inputString("작성자: ");
    b.registeredDate = new Date(System.currentTimeMillis());

    boardList.add(b);

    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }

  public void list() {
    System.out.println("[게시글 목록]");

    Board[] boards = boardList.toArray();

    for(Board b : boards) {

      System.out.printf("번호: %d 제목: %s 작성자: %s 등록일: %s\n조회수: %d 좋아요: %d\n",
          b.number, b.title, b.writer, b.registeredDate, b.viewCount, b.like);
      System.out.println("-------------------------------------------------------------");

    }
  }

  public void detail() {
    System.out.println("[게시글 상세보기]");

    Board board = boardList.get(Prompt.inputInt("번호? "));
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      board.viewCount++;
      System.out.printf("제목: %s ", board.title);
      System.out.printf("작성자: %s\n", board.writer);
      System.out.printf("내용: %s\n", board.content);
      System.out.printf("등록일: %s ", board.registeredDate);
      System.out.printf("조회수: %d\n", board.viewCount);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[게시글 수정하기]");

    Board board = boardList.get(Prompt.inputInt("번호? "));
    if(board == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String title = Prompt.inputString(String.format("제목(%s)? ",board.title));
      String content = Prompt.inputString(String.format("내용(%s)? ",board.content));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        board.title = title;
        board.content = content;
        Date reRegisteredDate = new Date(System.currentTimeMillis());
        board.registeredDate = reRegisteredDate;
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호? ");
    Board board = boardList.get(no);
    if(board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        boardList.delete(no);

        System.out.println("게시글을 삭제하였습니다.");
        System.out.println();

      }else {

        System.out.println("게시글 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

}
