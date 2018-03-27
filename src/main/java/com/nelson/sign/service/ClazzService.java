package com.nelson.sign.service;

import com.nelson.sign.entity.Clazz;

import javax.validation.Valid;

public interface ClazzService {
    Clazz addClazz(@Valid Clazz clazz);
}
