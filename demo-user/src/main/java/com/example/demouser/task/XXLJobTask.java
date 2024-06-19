package com.example.demouser.task;

import com.example.demouser.mapper.ReserveMapper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class XXLJobTask {
    @Autowired
    ReserveMapper reserveMapper;
    @Autowired
    RedissonClient redissonClient;
    @XxlJob("updateReserve")
    public void updateReserve(){
        RLock lock= redissonClient.getLock("updateReserve");
        try {
            lock.lock();
            reserveMapper.updateReserve(LocalDateTime.now());
        } finally {
            lock.unlock();
        }
    }
}
