package com.javanoteany.dict.dao;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.dict.entity.DataDictDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataDictDetailRepository extends BaseRepository<DataDictDetail> {

    List<DataDictDetail> findByTypeId(String typeId);

    DataDictDetail findByCodeAndTypeId(String code, String typeId);

    DataDictDetail findByNameAndTypeId(String name, String typeId);

}