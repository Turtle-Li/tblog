package com.turtle.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lijiayu
 * @date 2020/4/13
 * @description
 */
@Component
public class StopCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("####################################flyway已执行，退出中############################################");
        System.exit(0);
    }
}
