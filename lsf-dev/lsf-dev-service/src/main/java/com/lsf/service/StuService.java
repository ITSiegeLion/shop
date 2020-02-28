package com.lsf.service;

import com.lsf.pojo.Stu;

public interface StuService {

    public Stu getStuInfo(int id);

    public void saveStu(Stu stu);

    public void updateStu(Stu stu);

    public void deleteStu(int id);

}
