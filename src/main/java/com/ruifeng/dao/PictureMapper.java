package com.ruifeng.dao;

import com.ruifeng.entity.Picture;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    //获取图片列表
    List<Picture> findPictures(PageUtil pageUtil);

    //获取图片总数量
    int getTotalPictures(PageUtil pageUtil);

    //插入图片
    int insertPicture(Picture picture);

    //更新图片
    int updPicture(Picture record);

    //删除图片
    int delPicture(Integer id);

    //通过id获取图片
    Picture findPictureById(Integer id);

    //删除功能是使用的逻辑删除
    int deleteBatch(Integer[] idList);

}