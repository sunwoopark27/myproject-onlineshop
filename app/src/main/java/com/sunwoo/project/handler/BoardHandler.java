package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Board;
import com.sunwoo.util.Prompt;

public class BoardHandler {

  Node first;
  Node last;
  int size = 0;

  public void add() {
    System.out.println("[새 게시글]");
    Board b = new Board();

    b.number = Prompt.promptInt("번호: ");
    b.title = Prompt.promptString("제목: ");
    b.content = Prompt.promptString("내용: ");
    b.writer = Prompt.promptString("작성자: ");
    b.registeredDate = new Date(System.currentTimeMillis());

    Node node = new Node(b);

    if(first == null) {
      first = node;
      last = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }
    this.size++;

    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }

  public void list() {
    System.out.println("[게시글 목록]");

    Node cursor = first;

    while(cursor != null) {
      Board b = cursor.board;

      System.out.printf("번호: %d 제목: %s 작성자: %s 등록일: %s\n조회수: %d 좋아요: %d\n",
          b.number, b.title, b.writer, b.registeredDate, b.viewCount, b.like);
      System.out.println("-------------------------------------------------------------");

      cursor = cursor.next;
    }
  }

  public void detail() {
    System.out.println("[게시글 상세보기]");

    Board board = findByNo(Prompt.promptInt("번호? "));
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

    Board board = findByNo(Prompt.promptInt("번호? "));
    if(board == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String title = Prompt.promptString(String.format("제목(%s)? ",board.title));
      String content = Prompt.promptString(String.format("내용(%s)? ",board.content));

      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
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

    Board board = findByNo(Prompt.promptInt("번호? "));
    if(board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        Node cursor = first;
        while(cursor != null) {
          if(cursor.board == board) {
            if(first == last) { //노드가 하나일 경우
              first = null;
              last = null;
            }else if(cursor == first){ //첫번째 노드일 경우
              first = cursor.next;
              cursor.prev = null;
            }else if(cursor == last) { //마지막 노드일경우
              cursor.prev.next = null;
              last = cursor.prev;
            }else{//중간에 다른 노드들
              cursor.prev.next = cursor.next;
              if(cursor.next !=null) {
                cursor.next.prev = cursor.prev;
              }
            }
            this.size--;
            break;
          }
          cursor = cursor.next;
        }
        System.out.println("게시글을 삭제하였습니다.");


      }else {

        System.out.println("게시글 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int boardNo) {
    //    for(int i = 0; i < this.size; i++) {
    //      Board board = this.boards[i];
    //      if(boardNo == board.number) {
    //        return i;
    //      }
    //    }
    return -1;
  }

  Board findByNo(int boardNo) {
    Node cursor = first;
    while(cursor != null) {
      if(cursor.board.number == boardNo) {
        return cursor.board;
      }
      cursor = cursor.next;
    }
    return null;
  }

  static class Node{
    Board board;
    Node next;
    Node prev;

    Node(Board b){
      this.board = b;
    }
  }
}
