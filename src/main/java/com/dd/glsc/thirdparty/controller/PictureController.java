package com.dd.glsc.thirdparty.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dd.glsc.thirdparty.entity.PictureEntity;
import com.dd.glsc.thirdparty.service.PictureService;
import com.dd.common.utils.PageUtils;
import com.dd.common.utils.R;



/**
 * 
 *
 * @author dd
 * @email 18211882344@163.com
 * @date 2025-12-19 16:43:28
 */
@RestController
@RequestMapping("thirdparty/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("thirdparty:picture:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pictureService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("thirdparty:picture:info")
    public R info(@PathVariable("id") Long id){
		PictureEntity picture = pictureService.getById(id);

        return R.ok().put("picture", picture);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("thirdparty:picture:save")
    public R save(@RequestBody PictureEntity picture){
		pictureService.save(picture);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("thirdparty:picture:update")
    public R update(@RequestBody PictureEntity picture){
		pictureService.updateById(picture);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("thirdparty:picture:delete")
    public R delete(@RequestBody Long[] ids){
		pictureService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
