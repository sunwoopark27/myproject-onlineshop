package com.sunwoo.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
