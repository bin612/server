package com.example.server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> {

    private Header header;
    private T resBody; // body 는 게속 변경이 됨

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header {
        private String responseCode;
    }

}
