package com.dd.glsc.thirdparty.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author dd
 * @email 18211882344@163.com
 * @date 2025-12-19 16:43:28
 */
@Data
@TableName("picture")
public class PictureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String picName;
	/**
	 * 
	 */
	private String introduction;
	/**
	 * 
	 */
	private String category;
	/**
	 *
	 */
	private Long picSize;
	/**
	 *
	 */
	private Integer picWidth;
	/**
	 *
	 */
	private Integer picHeight;
	/**
	 *
	 */
	private Double picScale;
	/**
	 * 
	 */
	private String picFormat;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date editTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer isDelete;
	/**
	 * 
	 */
	private Integer isUsed;

}
