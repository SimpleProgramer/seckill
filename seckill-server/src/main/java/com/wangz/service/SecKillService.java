package com.wangz.service;

import com.wangz.model.req.SecKillRequest;
import com.wangz.model.resp.SecKillResponse;

/**
 * @author wangzun
 * @version 2018/11/7 下午4:32
 * @desc 秒杀逻辑
 */
public interface SecKillService {
    SecKillResponse try2Kill(SecKillRequest request);
}
