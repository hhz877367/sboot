package com.baizhi.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GmzProperties.class)
public class GmzConfig {
  private static String profile;

  @Value("${gmz.profile}")
  public void setProfile(String profile) {
    GmzConfig.profile = profile;
  }

  public static String getProfile() {
    return profile;
  }

  /**
   * 获取头像上传路径
   */
  public static final String getAvatarPath() {
    return getProfile() + "/avatar";
  }

  /**
   * 获取下载路径
   */
  public static final String getDownloadPath() {
    return getProfile() + "/download/";
  }

  /**
   * 获取上传路径
   */
  public static final String getUploadPath() {
    return getProfile() + "/upload/";
  }
}