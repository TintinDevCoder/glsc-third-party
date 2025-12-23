package com.dd.glsc.thirdparty.service.Impl;

import com.dd.glsc.thirdparty.entity.PictureUploadRequest;
import com.dd.glsc.thirdparty.entity.UploadPictureResult;
import com.dd.glsc.thirdparty.manager.upload.FilePictureUploadImpl;
import com.dd.glsc.thirdparty.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    FilePictureUploadImpl pictureUpload;

    @Override
    public UploadPictureResult uploadPicture(Object inputSource, PictureUploadRequest pictureUploadRequest) {
        UploadPictureResult uploadPictureResult;
        uploadPictureResult = pictureUpload.uploadPicture(inputSource, pictureUploadRequest);
        return uploadPictureResult;
    }
}
