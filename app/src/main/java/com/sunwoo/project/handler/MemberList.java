package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Member;

public class MemberList {
  private Node first;
  private Node last;
  private int size = 0;

  public void add(Member m) {
    Node node = new Node(m);

    if(first == null) {
      first = node;
      last = node;
    }else {
      node.prev = last;
      last.next = node;
      last = node;
    }
    this.size++;
  }

  public Member[] toArray() {

    Member[] arr = new Member[size];
    Node cursor = first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.member;
      cursor = cursor.next;
    }

    return arr;
  }

  public Member get(int memberNo) {

    Node cursor = first;
    while(cursor != null) {
      if(cursor.member.getNumber() == memberNo) {
        return cursor.member;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean exist(String name){
    Node cursor = first;
    while(cursor != null) {
      if(name.equals(cursor.member.getName())) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  public void delete(int memberNo) {

    Member member = get(memberNo);
    if(member == null) {
      return;
    }

    Node cursor = first;
    while(cursor != null) {
      if(cursor.member == member) {
        if(first == last) {
          first = null;
          last = null;
        }else if(cursor == first) {
          first = cursor.next;
          first.prev = null;
        }else if(cursor == last) {
          last = cursor.prev;
          last.next = null;
        }else {
          cursor.prev.next = cursor.next;
          if(cursor.next != null) {
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
    Member member;
    Node next;
    Node prev;

    Node(Member m){
      this.member = m;
    }
  }
}
