package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class NotePageRequest extends BaseRequest{
    private String bookNo;
    private Integer userId;
}
