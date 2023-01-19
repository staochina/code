package cn.joyjoyboy.code.springcode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

    public void test(int n){
        log.info("test {}",n);
    }
}
