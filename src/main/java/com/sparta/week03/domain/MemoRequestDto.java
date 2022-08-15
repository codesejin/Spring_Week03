package com.sparta.week03.domain;

import lombok.Getter;


@Getter
// final을 안적는 이유는 멀까..? 
public class MemoRequestDto {
    private String username;
    private String contents;
}
