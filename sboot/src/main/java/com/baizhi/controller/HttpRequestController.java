package com.baizhi.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/httpRequestController")
public class HttpRequestController implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest rquest, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("httpRequestController");

        Object zs = rquest.getAttribute("zs");
        System.out.println("参数为"+zs.toString());

    }
}
