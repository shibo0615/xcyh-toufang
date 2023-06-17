package com.xcyh.xcyhtoufang.dao.slave;

import com.xcyh.xcyhtoufang.pojo.Test001;
import org.apache.ibatis.annotations.Param;

public interface TestSlaveMapper {

    Test001 getTest001(@Param(value = "id") Integer id);

}
