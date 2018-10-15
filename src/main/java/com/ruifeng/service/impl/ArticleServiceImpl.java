package com.ruifeng.service.impl;

import com.ruifeng.dao.ArticleMapper;
import com.ruifeng.entity.Article;
import com.ruifeng.service.ArticleService;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author Administrator
 * @create 2018-10-11 17:29
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    //获取封装分页数据
    public PageResult getArticlePage(PageUtil pageUtil) {
        List<Article> articleList = articleMapper.findArticles(pageUtil);
        int total = articleMapper.getTotalArticles(pageUtil);
        PageResult pageResult = new PageResult(articleList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    //根据id获取对应详情
    @Override
    public Article queryObject(Integer id) {
        return articleMapper.getArticleById(id);
    }

    //获取列表数据
    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleMapper.findArticles(map);
        return articles;
    }

    //获取总数
    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleMapper.getTotalArticles(map);
    }

    //增加
    @Override
    public int save(Article article) {
        try {
            return articleMapper.insertArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //修改
    @Override
    public int update(Article article) {
        article.setUpdateTime(new Date());
        return articleMapper.updArticle(article);
    }

    //删除
    @Override
    public int delete(Integer id) {
        return articleMapper.delArticle(id);
    }

    //批量删除
    @Override
    public int deleteBatch(Integer[] ids) {
        return articleMapper.deleteBatch(ids);
    }
}
