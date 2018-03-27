package com.nelson.sign.controller;

import com.nelson.sign.entity.Clazz;
import com.nelson.sign.service.ClazzService;
import com.nelson.sign.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/clazz/api")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 添加班级
     * @param clazz
     * @return
     */
    @RequestMapping(value = "addClazz")
    public Result addClazz(@Valid Clazz clazz){
        clazz = this.clazzService.addClazz(clazz);
        Result result = new Result("OK");
        result.resultMap.put("clazz",clazz);
        return result;
    }

}
