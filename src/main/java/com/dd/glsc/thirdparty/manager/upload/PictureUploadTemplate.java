package com.dd.glsc.thirdparty.manager.upload;
import com.dd.common.common.BusinessException;
import com.dd.common.common.ErrorCode;
import com.dd.glsc.thirdparty.config.CosClientConfig;
import com.dd.glsc.thirdparty.entity.PictureEntity;
import com.dd.glsc.thirdparty.entity.PictureUploadRequest;
import com.dd.glsc.thirdparty.entity.UploadPictureResult;
import com.dd.glsc.thirdparty.manager.CosManager;
import com.dd.glsc.thirdparty.service.PictureService;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.persistence.CIObject;
import com.qcloud.cos.model.ciModel.persistence.ImageInfo;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

@Slf4j
public abstract class PictureUploadTemplate {
    @Resource
    protected CosManager cosManager;

    @Resource
    protected CosClientConfig cosClientConfig;

    @Autowired
    PictureService pictureService;
  
    /**  
     * 模板方法，定义上传流程  
     */  
    public final UploadPictureResult uploadPicture(Object inputSource, PictureUploadRequest uploadPathPrefix) {
        // 1. 校验图片  
        validPicture(inputSource);  
  
        // 2. 图片上传地址  
        String uuid = RandomUtil.randomString(16);
        String originFilename = getOriginFilename(inputSource);  
        String uploadFilename = String.format("%s_%s.%s", DateUtil.formatDate(new Date()), uuid,
                FileUtil.getSuffix(originFilename));
        String uploadPath = String.format("/%s/%s", uploadPathPrefix.getPrefixName(), uploadFilename);
  
        File file = null;  
        try {  
            // 3. 创建临时文件  
            file = File.createTempFile("tempfile" + uploadPathPrefix, null);
            // 处理文件来源（本地或 URL）  
            processFile(inputSource, file);
            // 4. 上传图片到对象存储  
            PutObjectResult putObjectResult = cosManager.putPictureObject(uploadPath, file);
            //获取到图片处理结果
            //获取到原始图片
            ImageInfo imageInfo = putObjectResult.getCiUploadResult().getOriginalInfo().getImageInfo();
            // 5. 封装返回结果
            UploadPictureResult uploadPictureResult = buildResult(originFilename, file, uploadPath, imageInfo);
            // 保存图片信息到数据库
            PictureEntity picture = new PictureEntity();
            BeanUtils.copyProperties(uploadPictureResult, picture);
            picture.setIsUsed(1);
            picture.setCreateTime(new Date());
            picture.setEditTime(new Date());
            picture.setPicName(uploadFilename);
            pictureService.save(picture);
            return uploadPictureResult;
        } catch (Exception e) {  
            log.error("图片上传到对象存储失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {  
            // 6. 清理临时文件  
            deleteTempFile(file);  
        }  
    }



    /**  
     * 校验输入源（本地文件或 URL）  
     */  
    protected abstract void validPicture(Object inputSource);  
  
    /**  
     * 获取输入源的原始文件名  
     */  
    protected abstract String getOriginFilename(Object inputSource);  
  
    /**  
     * 处理输入源并生成本地临时文件  
     */  
    protected abstract void processFile(Object inputSource, File file) throws Exception;

    /**
     * 封装返回结果（压缩后的）
     *
     * @param originFilename
     * @param compressedCiObject
     * @param thumbnailCiObject
     * @return
     */
    private UploadPictureResult buildResult(String originFilename, CIObject compressedCiObject, CIObject thumbnailCiObject,
                                            ImageInfo imageInfo) {
        UploadPictureResult uploadPictureResult = new UploadPictureResult();
        int picWidth = compressedCiObject.getWidth();
        int picHeight = compressedCiObject.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
        uploadPictureResult.setPicName(FileUtil.mainName(originFilename));
        uploadPictureResult.setPicWidth(picWidth);
        uploadPictureResult.setPicHeight(picHeight);
        uploadPictureResult.setPicScale(picScale);
        uploadPictureResult.setPicFormat(compressedCiObject.getFormat());
        uploadPictureResult.setPicSize(Long.valueOf(compressedCiObject.getSize()));
        //设置原图地址
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + compressedCiObject.getKey());
        return uploadPictureResult;
    }

    /**  
     * 封装返回结果（原始图片）
     */
    private UploadPictureResult buildResult(String originFilename, File file, String uploadPath, ImageInfo imageInfo) {
        UploadPictureResult uploadPictureResult = new UploadPictureResult();
        int picWidth = imageInfo.getWidth();
        int picHeight = imageInfo.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
        uploadPictureResult.setPicName(FileUtil.mainName(originFilename));  
        uploadPictureResult.setPicWidth(picWidth);  
        uploadPictureResult.setPicHeight(picHeight);  
        uploadPictureResult.setPicScale(picScale);  
        uploadPictureResult.setPicFormat(imageInfo.getFormat());  
        uploadPictureResult.setPicSize(FileUtil.size(file));  
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + uploadPath);
        return uploadPictureResult;  
    }  
  
    /**  
     * 删除临时文件  
     */  
    public void deleteTempFile(File file) {  
        if (file == null) {  
            return;  
        }  
        boolean deleteResult = file.delete();  
        if (!deleteResult) {  
            log.error("file delete error, filepath = {}", file.getAbsolutePath());  
        }  
    }


}
