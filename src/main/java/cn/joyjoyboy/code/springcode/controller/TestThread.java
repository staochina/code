package cn.joyjoyboy.code.springcode.controller;

import cn.joyjoyboy.code.springcode.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@RestController
@Slf4j
public class TestThread {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/hello")
    public void get() {
        //构建两个线程池
        ExecutorService executorService = new ScheduledThreadPoolExecutor(4);
        ExecutorService executorService2 = new ScheduledThreadPoolExecutor(2);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(Thread.currentThread().getName() + ">>线程名>>" + System.currentTimeMillis());
                    //启动线程池2
                    executorService2.submit(new Runnable() {
                        @Override
                        public void run() {
                            log.info("线程池2》》" + Thread.currentThread().getName());
                            //调用service方法
                            testService.test(4);
                        }
                    });
                }
            });
        }
    }
}
