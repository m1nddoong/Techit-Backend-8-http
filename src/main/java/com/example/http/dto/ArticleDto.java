package com.example.http.dto;

import lombok.Data;

// 사용자가 작성하고 싶은 게시글 데이터를 의미한다.
// 제목 : 직렬화 역직렬화
// 내용 : Serialization

/*
// JSON으로 표현된 데이터
{
    "title" : "직렬화 역직렬화",
    "content" : "Serialization"
}

// Java에서 실제로 사용하는 객체
Article dto = new ArticleDto();
dto.setTitle("작렬화 역직렬화");
dto.setContent("Serialization");

JSON -> Java 객체 : 역직렬화
Java 객체 -> JSON : 직렬화
 */

@Data
public class ArticleDto {
    private String title;
    private String content;

}
