package com.baizhi.redis;



import java.util.Arrays;
import java.util.Collection;

public class CacheHashCode {
  private Object[] params;
  private int code;

  private CacheHashCode(Object[] params) {
    this.params = params;
    this.code = Arrays.deepHashCode(params);
  }

  public static CacheHashCode of(Object object) {
    return new CacheHashCode(
        ArrayUtils.isArray(object) ? ArrayUtils.toObjectArray((Collection<?>) object) :
            new Object[]{object});
  }

  @Override
  public int hashCode() {
    return code;
  }

  public String hashString() {
    return code + "";
  }

  @Override
  public String toString() {
    return "CacheHashCode{" + "params=" + Arrays.toString(params) + ", code=" + code + '}';
  }
}
