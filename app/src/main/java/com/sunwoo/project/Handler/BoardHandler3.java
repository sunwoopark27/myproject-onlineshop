package com.sunwoo.project.Handler;

import java.sql.Date;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.Prompt;

public class BoardHandler3 {

  static final int LENGTH = 100;
  static Board[] boards = new Board[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[교환/반품 문의]");
    System.out.println("[새 게시글]");
    Board b = new Board();

    b.number = Prompt.promptInt("번호: ");
    b.title = Prompt.promptString("제목: ");
    b.content = Prompt.promptString("내용: ");
    b.writer = Prompt.promptString("작성자: ");
    b.registeredDate = new Date(System.currentTimeMillis());

    boards[size++] = b;
    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }

  public static void list() {
    System.out.println("[게시글 목록]");

    for(int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("번호: %d 제목: %s\n내용: %s\n작성자: %s 작성 날짜: %s\n조회수: %d 좋아요: %d\n",
          b.number, b.title, b.content, b.writer, b.registeredDate, b.viewCount, b.like);
      System.out.println("-------------------------------------------------------------");
    }
  }

}
