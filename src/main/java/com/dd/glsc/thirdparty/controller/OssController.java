package com.dd.glsc.thirdparty.controller;

import com.dd.common.common.BaseResponse;
import com.dd.common.common.ResultUtils;
import com.dd.glsc.thirdparty.entity.PictureUploadRequest;
import com.dd.glsc.thirdparty.entity.PictureVO;
import com.dd.glsc.thirdparty.entity.UploadPictureResult;
import com.dd.glsc.thirdparty.manager.upload.FilePictureUploadImpl;
import com.dd.glsc.thirdparty.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
@Slf4j
@RestController
public class OssController {

    @Autowired
    OssService pictureService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/picture/upload")
    public BaseResponse<UploadPictureResult> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest) {
        UploadPictureResult uploadPictureResult = pictureService.uploadPicture(multipartFile, pictureUploadRequest);
        return ResultUtils.success(uploadPictureResult);
    }

    public String UploadImage() throws Exception {
        return null;
    }
}