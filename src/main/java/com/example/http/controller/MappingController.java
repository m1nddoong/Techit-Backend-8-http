package com.example.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MappingController {

    // @RequestMapping 에 전달된 파라미터를 바탕으로
    // 어떤 HTTP 요청에 대해서 메서드가 실행이 되는지
    @RequestMapping(
            value = "/example/{pathVar}",
            // method : 어떤 HTTP 메서드에 대해 실행이 되는지 (중괄호로 복수 표현 가능)
            method = {RequestMethod.GET, RequestMethod.POST},
            // consumes : 어떤 데이터 형식에 대해 실행이 되는지
            // 요청의 Content-Type 헤더
            consumes = MediaType.APPLICATION_JSON_VALUE,
            // headers : 어떤 헤더가 포함되어야 실행이 되는지
            headers = "x-likelion=hello", // 이 헤더를 포함해야지만 실행되도록
            // params : 어떤 Query Parameter가 있어야 하는지
            params = "likelion=hello"
    )
    public String example(
            @PathVariable
            String pathVar
    ) {
        return "done";
    }

    // '/path' 로 오는 GET 또는 POST 요청에 대해서
    // 메서드를 실행하도록 @RequsetMapping 을 작성하시오.
    @RequestMapping(
            value = "/path",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public String getOrPost() {
        log.info("get or post");
        return "GET or POST";
    }

    @GetMapping(
            value = "/get-only",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String getJsonOnly() {
        return "GET with content-type: JSON";
    }
}



