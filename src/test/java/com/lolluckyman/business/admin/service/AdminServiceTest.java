package com.lolluckyman.business.admin.service;

import com.lolluckyman.business.admin.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/applicationContext.xml")
public class AdminServiceTest {

    @Autowired
    private IAdminService adminService;

    @Test
    public void testGetAdminPageList() throws Exception {

    }

    @Test
    public void testGetAdminByCode() throws Exception {

    }

    @Test
    public void testCreateAdmin() throws Exception {
        Admin admin=new Admin();
        admin.setLoginName("zs");
        admin.setPassword("123456");
        admin = adminService.createAdmin(admin);
        System.out.println(admin);
    }

    @Test
    public void testUpdateAdmin() throws Exception {
        Admin admin = adminService.getAdminByCode("201609211721140002");
        admin.setLoginName("ls");
        admin = adminService.updateAdmin(admin);
        System.out.println(admin);
    }

    @Test
    public void testDeleteAdmin() throws Exception {
        System.out.println(adminService.deleteAdmin("201609211721140002"));
    }

    @Test
    public void testGetAdminByLoginName() throws Exception {
        System.out.println(adminService.getAdminByLoginName("ls"));
    }
}