package hut.zj.dict.dao;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.dict.entity.DataDictDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataDictDetailRepository extends BaseRepository<DataDictDetail> {

    List<DataDictDetail> findByTypeId(String typeId);

    DataDictDetail findByCodeAndTypeId(String code, String typeId);

    DataDictDetail findByNameAndTypeId(String name, String typeId);

}