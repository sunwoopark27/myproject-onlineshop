package com.sunwoo.project.handler;

import java.sql.Date;
import java.util.List;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[새 게시글]");

    Board b = new Board();

    b.setNumber(Prompt.inputInt("번호: "));
    b.setTitle(Prompt.inputString("제목: "));
    b.setContent(Prompt.inputString("내용: "));
    b.setWriter(Prompt.inputString("작성자: "));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }
}
