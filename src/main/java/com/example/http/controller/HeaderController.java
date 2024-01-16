package com.example.http.controller;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HeaderController {
    // 사용자가 보낸 요청에 포함되어있는 하나를 가져오고 시다면 RequestHeader 를 쓸 수 있다?
    @PostMapping("/single-header") // 하나의 헤더만 받아와서 사용하겠다.
    @ResponseBody
    public String singleHeader(
            // HTTP 욫ㅇ에 포함된 Header 하ㅏㄴ ㅏ져오로 싶을떄
            @RequestHeader("Content-Type")
            String contentType
    ) {
        return contentType;
    }

    @PostMapping("/option-header")
    @ResponseBody
    public String optionHeader(
            @RequestHeader(
                    value = "x-likelion",
                    // required: 포함을 반드시 해야하는지
                    required = false
            )
            String likelionHeader,
            @RequestHeader(
                    value = "x-likelion",
                    // required: 포함을 반드시 해야하는지
                    required = false,
                    // defaultValue : 포함 안되었을 때 기본값
                    defaultValue = "hello"
            )
            String likelionDefaultHeader
    ) {
        log.info(likelionHeader);
        log.info(likelionDefaultHeader);
        return likelionHeader; // 값을 있는 그대로 반환
    }


    @PostMapping("/all-header")
    @ResponseBody
    public String allHeaders(
            // HttpHeaders, Map<String, String>를 사용하면
            // 전체 헤더를 다 확인할 수 있다.
//            @RequestHeader
//            Map<String, String> headers,
            @RequestHeader
            HttpHeaders headers
    ) {
        for (Map.Entry<String, List<String>> entry:
                headers.entrySet()) {
            log.info(String.format(
                    "%s: %s", entry.getKey(), entry.getValue()
            ));
        }
        return "done";
    }
}
