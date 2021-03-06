package com.on.spring.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class AddApplyRequest {
    private String applyName;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String hashTag;

    public String getApplyName() {
        return this.applyName;
    }
}
