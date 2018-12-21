package com.example.risha.vijaysetu_cometogeherforcause;

public class NGO {
    String NGOName;
    String NGOCategory;
    String NGODescription;
    String NGOPhone;

    public NGO() {
    }

    public NGO( String NGOName,String NGOCategory, String NGODescription, String NGOPhone) {
        this.NGOName = NGOName;
        this.NGOCategory=NGOCategory;
        this.NGODescription = NGODescription;
        this.NGOPhone = NGOPhone;
    }


    public String getNGOName() {
        return NGOName;
    }

    public void setNGOName(String NGOName) {
        this.NGOName = NGOName;
    }

    public String getNGODescription() {
        return NGODescription;
    }

    public void setNGODescription(String NGODescription) {
        this.NGODescription = NGODescription;
    }

    public String getNGOPhone() {
        return NGOPhone;
    }

    public void setNGOPhone(String NGOPhone) {
        this.NGOPhone = NGOPhone;
    }

    public String getNGOCategory() {
        return NGOCategory;
    }

    public void setNGOCategory(String NGOCategory) {
        this.NGOCategory = NGOCategory;
    }

}
