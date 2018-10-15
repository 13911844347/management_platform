package com.ruifeng.service.impl;

import com.ruifeng.dao.PictureMapper;
import com.ruifeng.entity.Picture;
import com.ruifeng.service.PictureService;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * @author Administrator
 * @create 2018-10-11 10:29
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 获取图片列表
     * @param pageUtil
     * @return
     */
    public PageResult getPicturePage(PageUtil pageUtil) {
        List<Picture> pictures = pictureMapper.findPictures(pageUtil);
        int total = pictureMapper.getTotalPictures(pageUtil);
        PageResult pageResult = new PageResult(pictures, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    /**
     * 通过id获取图片
     * @param id
     * @return
     */
    public Picture queryObject(Integer id) {
        return pictureMapper.findPictureById(id);
    }

    /**
     * 插入图片
     * @param picture
     * @return
     */
    public int save(Picture picture) {
        return pictureMapper.insertPicture(picture);
    }

    /**
     * 更新图片
     * @param picture
     * @return
     */
    public int update(Picture picture) {
        return pictureMapper.updPicture(picture);
    }

    /**
     * 删除图片
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return pictureMapper.delPicture(id);
    }

    /**
     * 删除功能是使用的逻辑删除
     * @param ids
     * @return
     */
    public int deleteBatch(Integer[] ids) {
        return pictureMapper.deleteBatch(ids);
    }
}
