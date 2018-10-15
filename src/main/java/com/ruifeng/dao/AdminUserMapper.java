package com.ruifeng.dao;

import com.ruifeng.entity.AdminUser;
import com.ruifeng.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    //查询用户数据
    AdminUser getAdminUserByUserNameAndPassword(AdminUser adminUser);
    //通过token查询用户数据
    AdminUser getAdminUserByToken(String userToken);
    //通过用户名查询用户数据
    AdminUser getAdminUserByuserName(String userName);
    //通过userId更新token
    Long updateUserToken(AdminUser adminUser);
    //查询用户列表
    List<AdminUser> findAdminUsers(PageUtil pageUtil);
    //查询用户总数
    int getTotalAdminUser(PageUtil pageUtil);
    //新增用户
    int addUser(AdminUser adminUser);
    //修改密码时将user_token也修改掉
    int updateUserPassword(AdminUser adminUser);
    //批量新增用户记录
    int insertUsersBatch(@Param("adminUsers") List<AdminUser> adminUsers);
    //查询所有用户列表
    List<AdminUser> getAllAdminUsers();
    //批量刪除用戶
    int deltedUsersBatch(Integer[] ids);

}