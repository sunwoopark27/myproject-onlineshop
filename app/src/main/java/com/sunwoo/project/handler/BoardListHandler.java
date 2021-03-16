package com.sunwoo.project.handler;

import java.util.Iterator;
import java.util.List;
import com.sunwoo.project.domain.Board;

public class BoardListHandler extends AbstractBoardHandler {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 목록]");

    Iterator<Board> iterator = boardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();

      System.out.printf("번호: %d 제목: %s 작성자: %s\n등록일: %s 조회수: %d\n",
          b.getNumber(), b.getTitle(), b.getWriter(), b.getRegisteredDate(), b.getViewCount());
      System.out.println("-------------------------------------------------------------");

    }
  }
}
