package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.ClazzRepository;
import com.nelson.sign.entity.Clazz;
import com.nelson.sign.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public Clazz addClazz(@Valid Clazz clazz) {
        return this.clazzRepository.saveAndFlush(clazz);
    }
}
