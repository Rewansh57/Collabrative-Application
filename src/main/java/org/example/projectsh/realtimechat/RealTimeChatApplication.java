package org.example.projectsh.realtimechat;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.server.WebServer;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class RealTimeChatApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder=new SpringApplicationBuilder(RealTimeChatApplication.class);
        builder.web(WebApplicationType.SERVLET);
        ServletWebServerApplicationContext context =
                (ServletWebServerApplicationContext) builder.run(args);

        Scanner sc=new Scanner(System.in);
        WebServer webServer=(context).getWebServer();

        boolean running=true;

        while(running){
            String command=sc.nextLine().trim().toLowerCase();

            switch(command){
                case "start":
                    webServer.start();
                    System.out.println("Web Server started");
                    break;
                case "stop":
                    webServer.stop();
                    System.out.println("Application closed");
                    break;
                case "exit":
                    webServer.stop();
                    context.close();
                    running =false;
                    System.out.println("Web Server stopped");

                    break;

            }

        }







    }

}
