package com.masjidtrpl.masjidku;

public class ModelSignUp {
    private String user, pass, pass1, email;

    public ModelSignUp() {
    }

    public ModelSignUp(String user, String email, String pass, String pass1) {
        this.user = user;
        this.pass = pass;
        this.pass1 = pass1;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
