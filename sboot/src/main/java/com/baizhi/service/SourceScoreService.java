package com.baizhi.service;

public interface SourceScoreService {
    Integer getSourceScore(int age, Integer gender,Integer number);

    Integer getSourceScoreIndex(int age, Integer gender,Integer number);
}
