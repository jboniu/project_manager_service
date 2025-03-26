package hut.zj.dict.dao;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.dict.entity.DataDictType;

public interface DataDictTypeRepository extends BaseRepository<DataDictType> {

    DataDictType findByCode(String code);

    DataDictType findByName(String name);
}