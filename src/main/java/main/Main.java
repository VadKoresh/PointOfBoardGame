package main;

import main.service.GetUpApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        GetUpApp getUpApp = new GetUpApp();
        getUpApp.getUp();
    }
}
