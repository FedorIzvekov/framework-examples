package com.fedorizvekov.http.server.servlet.service.impl;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import com.fedorizvekov.http.server.servlet.filter.CorsFilter;
import com.fedorizvekov.http.server.servlet.service.ServerService;
import com.fedorizvekov.http.server.servlet.servlet.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerServiceImpl implements ServerService {

    private final ServletContextHandler context;
    private final Server server;

    public ServerServiceImpl(ServletContextHandler servletContextHandler, Server server) {
        this.context = servletContextHandler;
        this.server = server;
    }


    @Override
    public void runServer() throws Exception {
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new UserServlet()), "/users");
        context.addFilter(new FilterHolder(new CorsFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(context);
        server.start();
        server.join();
    }

}
