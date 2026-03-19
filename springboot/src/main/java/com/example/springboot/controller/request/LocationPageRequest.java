package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class LocationPageRequest extends BaseRequest{
    private Integer floor;
    private Integer admin;
}
