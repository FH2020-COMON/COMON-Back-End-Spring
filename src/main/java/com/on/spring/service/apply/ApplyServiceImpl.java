package com.on.spring.service.apply;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ApplyServiceImpl implements ApplyService {
    @Value("${spring.file.path}")
    private String filePath;

    @Override
    public void uploadApply(List<MultipartFile> files, Long applyId) {
        int cur = 1;

        try {
        files.get(0).transferTo(new File(filePath + "apply/" + applyId + "/" + "preview.png"));
        files.remove(0);
            for (MultipartFile file : files) {
                file.transferTo(new File(filePath + "apply/"+ applyId + "/" + cur + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
