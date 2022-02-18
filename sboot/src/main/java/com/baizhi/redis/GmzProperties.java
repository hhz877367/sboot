package com.baizhi.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gmz")
@Data
public class GmzProperties {
  /**
   * 项目名称
   */
  private String name;

  /**
   * 版本
   */
  private String version;

  /**
   * 版权年份
   */
  private String copyrightYear;

  /**
   * 上传路径
   */
  private String profile;

  /**
   * 获取地址开关
   */
  private boolean addressEnabled;

  /**
   * 验证码开关
   */
  private boolean captchaEnabled;

  /**
   * 模块名称
   */
  private String module;

  /**
   * 重复提交时间
   */
  private int noRepeatTime;

}