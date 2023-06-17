package com.xcyh.xcyhtoufang.service;

import com.xcyh.xcyhtoufang.dao.master.TestMasterMapper;
import com.xcyh.xcyhtoufang.dao.slave.TestSlaveMapper;
import com.xcyh.xcyhtoufang.pojo.Test001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService001 {

    @Autowired
    private TestSlaveMapper testSlaveMapper;

    @Autowired
    private TestMasterMapper testMasterMapper;

    public Test001 getTest001(Integer id){
        return testSlaveMapper.getTest001(id);
    }

    public Test001 addTest001(Test001 test001){
        int resultCode = testMasterMapper.addTest001(test001);
//        if(resultCode > 0){
//        }
        return test001;
    }

}
