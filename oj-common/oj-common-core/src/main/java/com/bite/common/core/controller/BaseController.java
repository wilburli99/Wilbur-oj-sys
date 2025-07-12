package com.bite.common.core.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.bite.common.core.domain.R;
import com.bite.common.core.domain.TableDataInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class BaseController {
//       if (insert > 0) {
//        return R.ok();
//    }
//        return R.fail();

    public R<Void> toR(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }


//       if (result) {
//        return R.ok();
//    }
//        return R.fail();
    public R<Void> toR(boolean result) {
        return result ? R.ok() : R.fail();
    }


    public TableDataInfo getTableDataInfo(List<?> list) {
        //list == null || list.isEmpty()
        if (CollectionUtil.isEmpty(list)) {
            return TableDataInfo.empty();
        }
//        new PageInfo<>(list).getTotal(); //获取符合查询条件的数据的总数
//        return TableDataInfo.success(list, list.size());
        return TableDataInfo.success(list, new PageInfo<>(list).getTotal());
    }
}