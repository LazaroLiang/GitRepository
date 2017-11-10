/*
* author:JerryLiang
* date:2017.11.08
* funtion:
* */
package cn.xidian.edu.Dao;

public class PersonInfo {
//    person_id int(12) not null primary key AUTO_INCREMENT,
//    person_name varchar(50) not null,
//    person_sex bit default 1,
//    person_age int(3),
//    person_email varchar(50),
//    person_address varchar(100),
//    person_telphone char(11),
//    person_nation varchar(50),
//    person_idCardNo char(18)
    private int person_id;
    private String name;
    private boolean sex=true;
    private int person_age;
    private String person_email;
    private String person_address;
    private String person_telphone;
    private String person_nation;
    private String person_idCardNo;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getPerson_age() {
        return person_age;
    }

    public void setPerson_age(int person_age) {
        this.person_age = person_age;
    }

    public String getPerson_email() {
        return person_email;
    }

    public void setPerson_email(String person_email) {
        this.person_email = person_email;
    }

    public String getPerson_address() {
        return person_address;
    }

    public void setPerson_address(String person_address) {
        this.person_address = person_address;
    }

    public String getPerson_telphone() {
        return person_telphone;
    }

    public void setPerson_telphone(String person_telphone) {
        this.person_telphone = person_telphone;
    }

    public String getPerson_nation() {
        return person_nation;
    }

    public void setPerson_nation(String person_nation) {
        this.person_nation = person_nation;
    }

    public String getPerson_idCardNo() {
        return person_idCardNo;
    }

    public void setPerson_idCardNo(String person_idCardNo) {
        this.person_idCardNo = person_idCardNo;
    }
}
