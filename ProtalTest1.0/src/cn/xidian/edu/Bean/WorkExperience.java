package cn.xidian.edu.Bean;

import java.util.Date;

public class WorkExperience {
//    `id` int(11) NOT NULL AUTO_INCREMENT,
//  `company_id` int(11) DEFAULT NULL,
//  `start_time` date DEFAULT NULL,
//            `end_time` date DEFAULT NULL,
//            `work_position` varchar(10) DEFAULT NULL,
//  `work_duty` varchar(500) DEFAULT NULL,
    private int id;
    private int company_id;
    private Date start_time;
    private Date end_time;
    private String work_position;
    private String work_duty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getWork_position() {
        return work_position;
    }

    public void setWork_position(String work_position) {
        this.work_position = work_position;
    }

    public String getWork_duty() {
        return work_duty;
    }

    public void setWork_duty(String work_duty) {
        this.work_duty = work_duty;
    }
}
