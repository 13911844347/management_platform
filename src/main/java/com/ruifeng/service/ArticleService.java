package com.ruifeng.service;

import com.ruifeng.entity.Article;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11/011 17:29
 * @Description:
 */
public interface ArticleService {
    //获取封装分页数据
    PageResult getArticlePage(PageUtil pageUtil);

    //根据id获取对应详情
    Article queryObject(Integer id);

    //获取列表数据
    List<Article> queryList(Map<String, Object> map);

    //获取总数
    int queryTotal(Map<String, Object> map);

    //增加
    int save(Article article);

    //修改
    int update(Article article);

    //删除
    int delete(Integer id);

    //批量删除
    int deleteBatch(Integer[] ids);
}
