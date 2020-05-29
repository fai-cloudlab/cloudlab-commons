package com.fujitsu.cloudlab.commons.util;

import java.util.Collection;

public class CommonUtils {
  private CommonUtils() {}

  public static boolean isNotNullAndEmpty(String str) {
    return str != null && !str.isEmpty();
  }

  public static <T> boolean isNotNullAndEmpty(Collection<T> c) {
    return c != null && !c.isEmpty();
  }
}
