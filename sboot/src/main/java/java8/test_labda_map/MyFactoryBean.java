package java8.test_labda_map;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface MyFactoryBean<T> {

   T getObject() throws BeansException;
}
