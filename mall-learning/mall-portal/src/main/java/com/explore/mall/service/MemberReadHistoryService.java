package com.explore.mall.service;

import com.explore.mall.domain.MemberReadHistory;

import java.util.List;

public interface MemberReadHistoryService {
    /**
     * 会员浏览记录管理Service
     * create by liao on 2021/1/26
     */
    /**
     * 生成浏览记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     *批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
