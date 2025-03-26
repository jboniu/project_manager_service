package hut.zj.dict.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.dict.dao.DataDictDetailRepository;
import hut.zj.dict.entity.DataDictDetail;
import hut.zj.dict.service.IDataDictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictDetailServiceImpl extends BaseServiceImpl<DataDictDetail> implements IDataDictDetailService {

    @Autowired
    private DataDictDetailRepository dataDictDetailRepository;

    public DataDictDetailServiceImpl(BaseRepository<DataDictDetail> repository) {
        super(repository);
    }

    @Override
    public List<DataDictDetail> findByTypeId(String typeId) {
        return dataDictDetailRepository.findByTypeId(typeId);
    }

    @Override
    public DataDictDetail findByNameAndTypeId(String name, String typeId) {
        return dataDictDetailRepository.findByNameAndTypeId(name,typeId);
    }

    @Override
    public DataDictDetail findByCodeAndTypeId(String code, String typeId) {
        return dataDictDetailRepository.findByCodeAndTypeId(code,typeId);
    }
}
