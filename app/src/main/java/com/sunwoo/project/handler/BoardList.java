package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Board;

public class BoardList {
  Node first;
  Node last;
  int size = 0;

  void add(Board b) {
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

  }

  Board[] toArray() {

    Node cursor = first;
    Board[] arr = new Board[size];
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.board;
      cursor = cursor.next;
    }

    return arr;
  }

  Board get(int boardNo) {

    Node cursor = first;
    while(cursor != null) {
      if(cursor.board.number == boardNo) {
        return cursor.board;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete (int boardNo) {

    Board board = get(boardNo);

    if(board == null) { //사실 앞서 검증한 부분이지만 혹시 모를 상황을 대비해 개발자가 남겨두기도 한다.
      return;
    }

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
