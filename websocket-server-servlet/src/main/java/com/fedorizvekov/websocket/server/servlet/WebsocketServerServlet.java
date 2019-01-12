package com.fedorizvekov.websocket.server.servlet;

import java.util.Properties;
import com.fedorizvekov.websocket.server.servlet.servlet.WebsocketMessengerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebsocketServerServlet {

    public static void main(String[] args) throws Exception {

        var properties = new Properties();

        try (var input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        var port = Integer.parseInt(properties.getProperty("server.port"));

        var context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new WebsocketMessengerServlet()), "/messenger");

        var server = new Server(port);
        server.setHandler(context);
        server.start();
        server.join();

    }

}
