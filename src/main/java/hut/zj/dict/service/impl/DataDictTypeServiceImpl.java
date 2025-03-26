package hut.zj.dict.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.dict.dao.DataDictTypeRepository;
import hut.zj.dict.entity.DataDictType;
import hut.zj.dict.service.IDataDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataDictTypeServiceImpl extends BaseServiceImpl<DataDictType> implements IDataDictTypeService {

    @Autowired
    private DataDictTypeRepository dataDictTypeRepository;

    public DataDictTypeServiceImpl(BaseRepository<DataDictType> repository) {
        super(repository);
    }

    @Override
    public DataDictType findByCode(String code) {
        return dataDictTypeRepository.findByCode(code);
    }

    @Override
    public DataDictType findByName(String name) {
        return dataDictTypeRepository.findByName(name);
    }
}
