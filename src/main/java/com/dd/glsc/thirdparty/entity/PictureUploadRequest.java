package com.dd.glsc.thirdparty.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureUploadRequest implements Serializable {
  
    /**  
     * 图片 id（用于修改）
     */  
    private Long id;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 图片存放位置前缀名称
     */
    private String prefixName;

    private static final long serialVersionUID = 1L;  
}
