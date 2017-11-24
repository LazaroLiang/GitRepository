package cn.xidian.edu.Bean;

public class EducationInfo {
//    id int(11) not null auto_increment,
//    name varchar(50),
//    person_id int(11) not null,
//    education_experience_id int(11) not null,
    private int id;
    private String name;
    private int person_id;
    private int education_experience_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getEducation_experience_id() {
        return education_experience_id;
    }

    public void setEducation_experience_id(int education_experience_id) {
        this.education_experience_id = education_experience_id;
    }
}
