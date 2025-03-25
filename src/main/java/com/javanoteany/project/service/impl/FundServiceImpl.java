package com.javanoteany.project.service.impl;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.common.base.service.Impl.BaseServiceImpl;
import com.javanoteany.project.entity.Fund;
import com.javanoteany.project.service.IFundService;
import org.springframework.stereotype.Service;

@Service
public class FundServiceImpl extends BaseServiceImpl<Fund> implements IFundService {
    public FundServiceImpl(BaseRepository<Fund> repository) {
        super(repository);
    }
}
