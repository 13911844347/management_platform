package com.ruifeng.dao;

import com.ruifeng.entity.Article;
import com.ruifeng.utils.PageUtil;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    //列表查询
    List<Article> findArticles(Map<String, Object> map);
    //数量查询
    int getTotalArticles(Map<String, Object> map);
    //增加功能
    int insertArticle(Article record);
    //修改功能
    int updArticle(Article record);
    //删除功能
    int delArticle(Integer id);
    //通過id查詢
    Article getArticleById(Integer id);
    //批量刪除
    int deleteBatch(Integer[] id);
}