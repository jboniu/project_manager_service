package com.javanoteany.dict.dao;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.dict.entity.DataDictType;

public interface DataDictTypeRepository extends BaseRepository<DataDictType> {

    DataDictType findByCode(String code);

    DataDictType findByName(String name);
}