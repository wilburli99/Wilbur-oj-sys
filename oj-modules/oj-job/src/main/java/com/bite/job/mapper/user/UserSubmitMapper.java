package com.bite.job.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bite.job.domain.user.UserScore;
import com.bite.job.domain.user.UserSubmit;

import java.util.List;
import java.util.Set;

public interface UserSubmitMapper extends BaseMapper<UserSubmit> {

//    where examId in(1,2,3)
    List<UserScore> selectUserScoreList(Set<Long> examIdSet);

    List<Long> selectHostQuestionList();
}
