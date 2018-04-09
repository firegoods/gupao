package com.lss.mybatis.v1;

import com.lss.mybatis.beans.Test;

/**
 * Created by liushaoshuai on 2018/4/9.
 */
public interface TestMapper {

    Test selectByPrimaryKey(String id);
}
