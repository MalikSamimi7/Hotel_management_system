package controllers;

public class customermodel {
    String id;
    String firstname;
    String lastname;
    String phone;
    String totalpayment;
    String checkedindate;
    String checkedoutdate;

    public customermodel(String id, String firstname, String lastname, String phone, String totalpayment, String checkedindate, String checkedoutdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.totalpayment = totalpayment;
        this.checkedindate = checkedindate;
        this.checkedoutdate = checkedoutdate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTotalpayment(String totalpayment) {
        this.totalpayment = totalpayment;
    }

    public void setCheckedindate(String checkedindate) {
        this.checkedindate = checkedindate;
    }

    public void setCheckedoutdate(String checkedoutdate) {
        this.checkedoutdate = checkedoutdate;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getTotalpayment() {
        return totalpayment;
    }

    public String getCheckedindate() {
        return checkedindate;
    }

    public String getCheckedoutdate() {
        return checkedoutdate;
    }
}
