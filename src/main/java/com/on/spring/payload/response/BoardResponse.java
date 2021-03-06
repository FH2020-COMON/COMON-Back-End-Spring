package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class BoardResponse {
    private String boardName;
    private Long boardId;
}
