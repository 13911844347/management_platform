package com.ruifeng.service.impl;

import com.ruifeng.dao.AdminUserMapper;
import com.ruifeng.entity.AdminUser;
import com.ruifeng.service.AdminUserService;
import com.ruifeng.utils.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * @author Administrator
 * @create 2018-10-10 10:17
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUser login(String userName, String passWordMd5) {
        String pwd = MD5Util.MD5Encode(passWordMd5, "UTF-8");
        AdminUser admin = new AdminUser();
        admin.setUserName(userName);
        admin.setPasswordMd5(pwd);
        AdminUser adminUser = adminUserMapper.getAdminUserByUserNameAndPassword(admin);
        if (adminUser != null) {
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", adminUser.getId());
            admin.setUserToken(token);
            admin.setId(adminUser.getId());
            long tokens = adminUserMapper.updateUserToken(admin);
            if ( tokens > 0) {
                //返回数据时带上token
                adminUser.setUserToken(token);
                return adminUser;
            }
        }
        return null;
    }

    /**
     * 获取token值
     *
     * @param sessionId
     * @param userId
     * @return
     */
    private String getNewToken(String sessionId, Long userId) {
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    /**
     * 根据token查询用户数据
     * @param userToken
     * @return
     */
    public AdminUser getAdminUserByToken(String userToken) {
        return adminUserMapper.getAdminUserByToken(userToken);
    }

    /**
     * 根据用户名查询用户数据
     * @return
     */
    public AdminUser selectByUserName(String userName) {
        return adminUserMapper.getAdminUserByuserName(userName);
    }

    /**
     * 根据用户id查询用户数据
     * @return
     */
    public AdminUser selectById(Long id) {
        return adminUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询用户列表以及数量
     * @param pageUtil
     * @return
     */
    public PageResult getAdminUserPage(PageUtil pageUtil) {
        //用户总数
        int total = adminUserMapper.getTotalAdminUser(pageUtil);
        //当前页的用户列表
        List<AdminUser> users = adminUserMapper.findAdminUsers(pageUtil);
        //分页信息封装
        PageResult pageResult = new PageResult(users, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    /**
     * 新增用户
     */
    public int save(AdminUser user) {
        //密码加密
        user.setPasswordMd5(MD5Util.MD5Encode(user.getPasswordMd5(), "UTF-8"));
        user.setUserToken(NumberUtil.getUUID32());
        return adminUserMapper.addUser(user);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int updatePassword(AdminUser user) {
        String pwd = MD5Util.MD5Encode(user.getPasswordMd5(), "UTF-8");
        user.setPasswordMd5(pwd);
        return adminUserMapper.updateUserPassword(user);
    }

    /**
     * 批量删粗
     * @param id
     * @return
     */
    public int deltedUsersBatch(Integer[] id) {
        return adminUserMapper.deltedUsersBatch(id);
    }


    public int importUsersByExcelFile(File file) {
        XSSFSheet xssfSheet = null;
        try {
            //读取file对象并转换为XSSFSheet类型对象进行处理
            xssfSheet = PoiUtil.getXSSFSheet(file);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        List<AdminUser> adminUsers = new ArrayList<>();
        //第一行是表头因此默认从第二行读取
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            //按行读取数据
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                //实体转换
                AdminUser adminUser = convertXSSFRowToAdminUser(xssfRow);
                //用户验证 已存在或者为空则不进行insert操作
                if (!StringUtils.isEmpty(adminUser.getUserName()) && !StringUtils.isEmpty(adminUser.getPasswordMd5()) && selectByUserName(adminUser.getUserName()) == null) {
                    adminUsers.add(adminUser);
                }
            }
        }
        //判空
        if (!CollectionUtils.isEmpty(adminUsers)) {
            //adminUsers用户列表不为空则执行批量添加sql
            return adminUserMapper.insertUsersBatch(adminUsers);
        }
        return 0;
    }

    @Override
    public List<AdminUser> getUsersForExport() {
        return adminUserMapper.getAllAdminUsers();
    }

    /**
     * 方法抽取
     * 将解析的列转换为AdminUser对象
     *
     * @param xssfRow
     * @return
     */
    private AdminUser convertXSSFRowToAdminUser(XSSFRow xssfRow) {
        AdminUser adminUser = new AdminUser();
        //用户名
        XSSFCell userName = xssfRow.getCell(0);
        //密码
        XSSFCell orinalPassword = xssfRow.getCell(1);
        //设置用户名
        if (!StringUtils.isEmpty(userName)) {
            adminUser.setUserName(PoiUtil.getValue(userName));
        }
        //对读取的密码进行加密并设置到adminUser对象中
        if (!StringUtils.isEmpty(orinalPassword)) {
            adminUser.setPasswordMd5(MD5Util.MD5Encode(PoiUtil.getValue(orinalPassword), "UTF-8"));
        }
        return adminUser;
    }
}
