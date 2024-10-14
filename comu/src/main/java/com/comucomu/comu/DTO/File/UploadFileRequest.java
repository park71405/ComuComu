package com.comucomu.comu.DTO.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UploadFileRequest {

    private String uploadFilename;
    private String storeFilename;


}
