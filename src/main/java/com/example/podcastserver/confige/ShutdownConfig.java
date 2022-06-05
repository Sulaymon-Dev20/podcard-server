package com.example.podcastserver.confige;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class ShutdownConfig {

    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }

    static class TerminateBean {
        @PreDestroy
        public void onDestroy() throws Exception {
//            Logger.info("project down bro :((");
//            Logger.info(new String(Runtime.getRuntime().exec("curl -X POST https://textbelt.com/text --data-urlencode phone='+998909476154' --data-urlencode message='Assalamu-alaykum-Xojiaka-project-o`chib-qoldiyov:))' -d key=textbelt").getInputStream().readAllBytes()));
        }
    }
}
