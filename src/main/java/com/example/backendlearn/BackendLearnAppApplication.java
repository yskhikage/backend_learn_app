package com.example.backendlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Bootアプリの起動クラス。
// main()を実行するとSpringが必要な部品(Controllerなど)を自動で組み立てて、
// 内蔵のWebサーバー(Tomcat)を起動してくれる。
@SpringBootApplication
public class BackendLearnAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLearnAppApplication.class, args);
    }
}
