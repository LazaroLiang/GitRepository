package cn.xidian.edu.Bean;

public class SchoolInfo {
//    id int(11) not null auto_increment,
//    school_name varchar(50) not null,
//    school_level varchar(10) not null,
//    school_rate	varchar(10),
//    school_address varchar(100),
    private int id;
    private String school_name;
    private String school_level;
    private String school_rate;
    private String school_address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_level() {
        return school_level;
    }

    public void setSchool_level(String school_level) {
        this.school_level = school_level;
    }

    public String getSchool_rate() {
        return school_rate;
    }

    public void setSchool_rate(String school_rate) {
        this.school_rate = school_rate;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }
}
