package cn.xidian.edu.Bean;

public class CompanyInfo {
//    id int(11) not null auto_increment,
//    company_name varchar(50) not null,
//    company_address varchar(100),
//    company_email varchar(20),
//    primary key (id)
    private int id;
    private String company_name;
    private String company_address;
    private String company_email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }
}
