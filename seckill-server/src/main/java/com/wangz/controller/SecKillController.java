package com.wangz.controller;

import com.wangz.controller.base.BaseController;
import com.wangz.model.req.SecKillRequest;
import com.wangz.model.resp.SecKillResponse;
import com.wangz.models.resp.ApiResponse;
import com.wangz.service.SecKillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "秒杀api")
public class SecKillController extends BaseController {

    static final String API_PATH = "/api/v1/skr";

    @Autowired
    private SecKillService seckillService;

    @ApiOperation(value = "发起秒杀行为",notes = "根据userId，productId进行秒杀")
    @PostMapping("/try2Kill")
    public ApiResponse<SecKillResponse> try2Kill(@RequestBody SecKillRequest request) {
        return apiResponse(seckillService.try2Kill(request));
    }
}
