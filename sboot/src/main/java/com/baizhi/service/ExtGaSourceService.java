package com.baizhi.service;

import com.baizhi.entity.excelEntity.SourceOutPdfVo;

import java.util.List;
import java.util.Map;

/**
 * 用户成绩业务
 *
 * @author wyy
 */
public interface ExtGaSourceService {


  /**
   * 军体通用五项-导出zip压缩包
   * @param listVo
   */
  Map<String, Object> getPdfByListSourceOutPdfVo(List<SourceOutPdfVo> listVo,String uuid,String threadName,String index);


}
