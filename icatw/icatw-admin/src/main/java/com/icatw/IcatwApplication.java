package com.icatw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author icatw
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IcatwApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(IcatwApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  icatw管理平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " _                  _              \n" +
                "(_)                | |             \n" +
                " _    ___    __ _  | |_  __      __\n" +
                "| |  / __|  / _` | | __| \\ \\ /\\ / /\n" +
                "| | | (__  | (_| | | |_   \\ V  V / \n" +
                "|_|  \\___|  \\__,_|  \\__|   \\_/\\_/  \n");

    }
}
