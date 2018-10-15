package com.ruifeng.service;

import com.ruifeng.entity.Picture;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11/011 10:29
 * @Description:
 */
public interface PictureService {

    /**
     * 获取图片列表
     * @param pageUtil
     * @return
     */
    PageResult getPicturePage(PageUtil pageUtil);

    /**
     * 通过id获取图片
     * @param id
     * @return
     */
    Picture queryObject(Integer id);

    /**
     * 插入图片
     * @param picture
     * @return
     */
    int save(Picture picture);

    /**
     * 更新图片
     * @param picture
     * @return
     */
    int update(Picture picture);

    /**
     * 删除图片
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 删除功能是使用的逻辑删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
}
