package com.baizhi.redis;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArrayUtils {
  /**
   * * 判断一个对象是否是数组类型（Java基本型别的数组）
   *
   * @param object 对象
   * @return true：是数组 false：不是数组
   */
  public static boolean isArray(Object object) {
    return Objects.nonNull(object) && object.getClass().isArray();
  }

  /**
   * 集合转数组
   *
   * @param collection 集合
   * @param clazz      类型
   * @param <T>        a T object.
   * @return 数组
   */
  @SuppressWarnings("unchecked")
  public static final <T> T[] toArray(final Collection<T> collection, final Class<T> clazz) {
    if (Objects.isNull(collection)) {
      return null;
    }

    final T[] arr = (T[]) Array.newInstance(clazz, collection.size());

    return collection.toArray(arr);
  }

  /**
   * 集合转对象数组
   *
   * @param collection 集合
   * @return 对象数组
   */
  public static final Object[] toObjectArray(final Collection<?> collection) {
    if (collection == null) {
      return null;
    }

    return collection.parallelStream().toArray(Object[]::new);
  }
  /**
   * 将一个map组成的list转成实体类bean组成的list
   * @param mapList 存了map对象的list
   * @param clazz 需要将这些map转成哪个实体类对象
   * @return
   */
  public static <T> List<T> convertMapListToBeanList(List<Map> mapList, Class<T> clazz){
    List<T> list=new ArrayList<T>();
    for(Map map:mapList){
      try {
        T obj=clazz.newInstance();//创建bean的实例对象
        for(Object o:map.keySet()){//遍历map的key
          for(Method m:clazz.getMethods()){//遍历bean的类中的方法，找到set方法进行赋值
            if(m.getName().toLowerCase().equals("set"+o.toString().toLowerCase())){
              m.invoke(obj, map.get(o));
            }
          }
        }
        list.add(obj);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

}
