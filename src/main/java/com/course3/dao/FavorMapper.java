package com.course3.dao;

import com.course3.beans.Favor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavorMapper {
    List<Favor> getFavorListByUid(Integer uid);
    Integer getFavorCountByAid(Integer aid);
    Integer getFavorCountByUid(Integer uid);
    boolean getIsFavorByFavor(Favor favor);
    boolean addFavor(Favor favor);
    boolean deleteFavor(Favor favor);
}
