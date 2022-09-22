package library.domain;

public class Admin {
    private String AdminID;
    private String Password;
    private String Name;
    private String Tel;

    public Admin() {
        
    }

    public Admin(String adminID, String password, String name, String tel) {
        AdminID = adminID;
        Password = password;
        Name = name;
        Tel = tel;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
