package com.YongChang.config;

import com.baomidou.mybatisplus.toolkit.IdWorker;

public class IdWorkerUtil {

    public static final String getId(){
        return "YongChang"+ IdWorker.getId();
    }
}
