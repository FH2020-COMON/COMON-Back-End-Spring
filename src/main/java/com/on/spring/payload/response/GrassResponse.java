package com.on.spring.payload.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GrassResponse {
    private int size;

    GrassResponse() {
        this.size = 0;
    }

    public void sizeUp() {
        this.size++;
    }
}
