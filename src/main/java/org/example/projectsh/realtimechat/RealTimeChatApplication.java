package org.example.projectsh.realtimechat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class RealTimeChatApplication {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SpringApplication app=new SpringApplication(RealTimeChatApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context =app.run(args);

        TomcatServletWebServerFactory factory=context.getBean(TomcatServletWebServerFactory.class);
        int port=sc.nextInt();
        sc.nextLine();

        factory.setPort(port);

        WebServer webServer=factory.getWebServer();






        boolean running = true;

        while (running) {
            String command = sc.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    webServer.start();
                    System.out.println("Web Server started");
                    break;
                case "stop":
                    webServer.stop();
                    System.out.println("Web Server stopped");
                    break;
                case "exit":
                    webServer.stop();
                    context.close();
                    running = false;
                    System.out.println("Application exited");
                    break;
                default:
                    System.out.println("Unknown command. Use: start | stop | exit");
            }
        }


    }
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();

    }

}
