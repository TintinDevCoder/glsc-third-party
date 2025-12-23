package com.dd.glsc.thirdparty.service.Impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dd.common.utils.PageUtils;
import com.dd.common.utils.Query;

import com.dd.glsc.thirdparty.dao.PictureDao;
import com.dd.glsc.thirdparty.entity.PictureEntity;
import com.dd.glsc.thirdparty.service.PictureService;


@Service("pictureService")
public class PictureServiceImpl extends ServiceImpl<PictureDao, PictureEntity> implements PictureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PictureEntity> page = this.page(
                new Query<PictureEntity>().getPage(params),
                new QueryWrapper<PictureEntity>()
        );

        return new PageUtils(page);
    }

}