package com.example.http.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ServletController {
    @PostMapping("/servlet")
    // 원래 Java EE 스타일로 HttpServletRequest / Response
    // 객체를 직접 다룰 수 있다.
    public void servletHandling(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        BufferedReader reader = request.getReader();
        reader.lines().forEach(log::info);
        response.setStatus(200);
    }
}




