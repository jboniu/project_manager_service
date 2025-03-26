package hut.zj.project.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.project.entity.Fund;
import hut.zj.project.service.IFundService;
import org.springframework.stereotype.Service;

@Service
public class FundServiceImpl extends BaseServiceImpl<Fund> implements IFundService {
    public FundServiceImpl(BaseRepository<Fund> repository) {
        super(repository);
    }
}
