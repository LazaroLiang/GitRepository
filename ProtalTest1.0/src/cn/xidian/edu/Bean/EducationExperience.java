package cn.xidian.edu.Bean;

import java.util.Date;

public class EducationExperience {
//    id int(11) not null auto_increment,
//    school_id int(11),
//    intend_time date,
//    graduate_time date,
    private int id;
    private int school_id;
    private Date intend_time;
    private Date graduate_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public Date getIntend_time() {
        return intend_time;
    }

    public void setIntend_time(Date intend_time) {
        this.intend_time = intend_time;
    }

    public Date getGraduate_time() {
        return graduate_time;
    }

    public void setGraduate_time(Date graduate_time) {
        this.graduate_time = graduate_time;
    }
}
