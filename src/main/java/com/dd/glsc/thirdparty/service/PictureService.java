package com.dd.glsc.thirdparty.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dd.common.utils.PageUtils;
import com.dd.glsc.thirdparty.entity.PictureEntity;

import java.util.Map;

/**
 * 
 *
 * @author dd
 * @email 18211882344@163.com
 * @date 2025-12-19 16:43:28
 */
public interface PictureService extends IService<PictureEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

