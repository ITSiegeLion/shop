package com.lsf.service.impl;

import com.lsf.mapper.StuMapper;
import com.lsf.pojo.Stu;
import com.lsf.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu(Stu stu) {
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(Stu stu) {
        stuMapper.updateByPrimaryKeySelective(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
