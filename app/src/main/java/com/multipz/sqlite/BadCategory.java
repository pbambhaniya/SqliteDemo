package com.multipz.sqlite;

/**
 * Created by Admin on 10-03-2018.
 */

public class BadCategory {
    public BadCategory(int id, String contain) {
        this.id = id;
        this.contain = contain;
    }

    int id;
    String contain;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }



}