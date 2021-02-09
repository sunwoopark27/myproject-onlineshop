package com.sunwoo.util;

public class QueueIterator extends AbstractIterator {

  Queue queue;

  public QueueIterator(Queue queue) throws CloneNotSupportedException {
    this.queue = queue.clone();
  }

  @Override
  public boolean hasNext() {
    return this.queue.size > 0;
  }

  @Override
  public Object next() {
    return this.queue.poll();
  }

}
