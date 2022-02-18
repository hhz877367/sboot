package com.baizhi.redis;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

public class CacheKeyConversionService implements ConversionService {

  @Override
  public boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType) {
    return true;
  }

  @Override
  public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
    return true;
  }

  @Nullable
  @Override
  public <T> T convert(@Nullable Object source, Class<T> targetType) {
    return (T) convert(source);
  }

  @NotNull
  @Override
  public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType,
      TypeDescriptor targetType) {
    return convert(source);
  }

  private Object convert(Object source) {
    if (source instanceof CacheHashCode) {
      return ((CacheHashCode) source).hashString();
    }

    return CacheHashCode.of(source).hashString();
  }
}
