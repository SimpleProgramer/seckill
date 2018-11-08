package com.wangz.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzun
 * @version 2018/11/7 下午4:32
 * @desc
 */
@Data
@AllArgsConstructor
public class SecKillResponse implements Serializable {

    private static final long serialVersionUID = 4417131727316512060L;
    private String msg;
}
