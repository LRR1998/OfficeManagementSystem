package com.ujiuye.compare.service;

import com.ujiuye.compare.bean.Comparision;

import java.util.List;

public interface ComparisionService {
    void saveInfo(Comparision comparision);

    List<Comparision> getComparisions();
}
