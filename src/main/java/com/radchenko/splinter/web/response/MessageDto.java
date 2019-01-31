package com.radchenko.splinter.web.response;

import lombok.Data;

@Data
public class MessageDto {
    private Long id;
    private String text;
    private String tag;
}
