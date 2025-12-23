package com.dd.glsc.thirdparty.service;

import com.dd.glsc.thirdparty.entity.PictureUploadRequest;
import com.dd.glsc.thirdparty.entity.PictureVO;
import com.dd.glsc.thirdparty.entity.UploadPictureResult;

public interface OssService {
    /**
     * 上传图片
     * @param inputSource 文件输入源
     * @param pictureUploadRequest
     * @return
     */
    UploadPictureResult uploadPicture(Object inputSource, PictureUploadRequest pictureUploadRequest);


}
