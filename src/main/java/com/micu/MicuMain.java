package com.micu;

import com.micu.config.MyApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MicuMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MicuMain.class, args);
        MyApplicationContextProvider.setApplicationContext(configurableApplicationContext);

    }

}
