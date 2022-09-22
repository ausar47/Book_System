package library.domain;

public class Cards {
    private String Id;
    private String Name;
    private String Department;
    private String Type;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Cards(String id, String name, String department, String type) {
        Id = id;
        Name = name;
        Department = department;
        Type = type;
    }

    public Cards() {

    }
}
