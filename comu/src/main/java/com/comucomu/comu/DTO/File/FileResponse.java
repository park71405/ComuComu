package com.comucomu.comu.DTO.File;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileResponse {

    private int fileNo;
    private String originalName;
    private String path;
    private long size;

    @Builder
    public File(int fileNo, String originalName, String path, long size){
        this.fileNo = fileNo;
        this.originalName = originalName;
        this.path = path;
        this.size = size;
    }

}
