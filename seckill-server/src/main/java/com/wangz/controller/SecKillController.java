package com.wangz.controller;

import com.wangz.controller.base.BaseController;
import com.wangz.model.resp.base.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wangz.controller.SecKillController.API_PATH;

/**
 * @author wangzun
 * @version 2018/11/7 下午2:52
 * @desc
 */
@RestController
@RequestMapping(API_PATH)
public class SecKillController extends BaseController {

    static final String API_PATH = "/api/v1/skr";

    @PostMapping("/try2Kill")
    public ApiResponse<Object> try2Kill() {
        return null;
    }
}
