package com.ruifeng.service;

import com.ruifeng.entity.AdminUser;
import com.ruifeng.utils.PageResult;
import com.ruifeng.utils.PageUtil;

import java.io.File;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/10/010 10:17
 * @Description:
 */
public interface AdminUserService {

    //登陆
    AdminUser login(String userName, String passWordMd5);
    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    AdminUser getAdminUserByToken(String userToken);

    /**
     * 通过用户名查询用户数据
     * @param userName
     * @return
     */
    AdminUser selectByUserName(String userName);
    /**
     * 通过用户id查询用户数据
     * @return
     */
    AdminUser selectById(Long id);
    /**
     * 查询用户列表以及数量
     * @param pageUtil
     * @return
     */
    PageResult getAdminUserPage(PageUtil pageUtil);

    /**
     * 新增用户
     */
    int save(AdminUser user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int updatePassword(AdminUser user);

    /**
     * 根据excel导入用户记录
     *
     * @param file
     * @return
     */
    int importUsersByExcelFile(File file);

    /**
     * 获取导出数据
     *
     * @return
     */
    List<AdminUser> getUsersForExport();

    /**
     * 批量刪除用戶
     * @param id
     * @return
     */
    int deltedUsersBatch(Integer[] id);

}
