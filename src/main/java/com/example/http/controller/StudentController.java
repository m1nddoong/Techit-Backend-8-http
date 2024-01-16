package com.example.http.controller;

import com.example.http.dto.StudentDto;
import com.example.http.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @Controller
// 모둔 하위 메서드 @ResponseBody 를 추가하는 애노테이션
@RestController
public class StudentController {
    @PostMapping("student")
    // @ResponseBody
    public StudentDto newStudent(
            @RequestBody
            StudentDto dto
    ) {
        log.info(dto.toString());
        dto.setAge(dto.getAge() + 1);
        return dto;
    }

    // StudentDto 형태의 JSON 데이터를 받아서
    /*
    {
        "message" : "등록 완료" // 우리가 이전에 정의해놓은 Dto 가 아님을 인지해야함.
    }
     */
    // 라는 JSON 을 반환하도록
    // @PutMapping("student")
    @RequestMapping(
            value = "student",
            method = RequestMethod.PUT
    )
    // @ResponseBody
    public ResponseDto putStudent(
            @RequestBody
            StudentDto studentDto // 학생의 데이터를 사용자에서부터 받아오자.
    ) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("등록 완료");
        return responseDto;
    }


    // 이번에는 ResponseBody 를 생략할 것
    @PostMapping("student/entity")
    public ResponseEntity<ResponseDto> postEntity(
            @RequestBody
            StudentDto studentDto
    ) {
        log.info(studentDto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("등록 완료");
        // 인자로 받았던 HttpHeaders 을 추가해보자
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-likelion-response", "OK");
        headers.add("x-likelion-sequence", "Yes");
        // return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("student/bad-request")
    public ResponseEntity<ResponseDto> badRequest(
            @RequestBody
            StudentDto studentDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        // 두가지의 경우를 나눠서 생각해보자
        // 10살 미만 -> bad Request
        if (studentDto.getAge() < 10) {
            // responseDto의 message에 "너무 어려요"
            responseDto.setMessage("너무 어려요");
            // 응답코드 400으로 응답
            return new ResponseEntity<>(
                    responseDto, HttpStatus.BAD_REQUEST
            );
        }

        // 10살 이상은 -> OK
        else {
            // message 에 "등록 완료"
            // 응답코드 200으로 응답
            responseDto.setMessage("등록 완료");
            return new ResponseEntity<>(
                    responseDto, HttpStatus.OK
            );

        }

    }
}





