package library.service;

import library.dao.AdminDAO;
import library.domain.Admin;

public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();

    public Admin getAdminByIdAndPwd(String AdminID, String pwd) {
        return adminDAO.querySingle("select * from admin where AdminID = ? and Password = md5(?)", Admin.class, AdminID, pwd);
    }
}
