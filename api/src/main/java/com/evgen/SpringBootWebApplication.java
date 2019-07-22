package com.evgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Главный класс, выполняющий запуск приложения
 * @author Eugene +375-29-702-28-51
 * */
@SpringBootApplication
    public class SpringBootWebApplication extends SpringBootServletInitializer {

        /**
         * Метод выполняет конфигурацию Spring-приложения
         * @param application
         * */
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SpringBootWebApplication.class);
        }

        /**
         * Главный метод выполняет запуск Spring-приложения и активацию бота
         * */
        public static void main(String[] args) {
            SpringApplication.run(SpringBootWebApplication.class, args);
        }
    }

