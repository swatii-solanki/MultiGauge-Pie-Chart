package com.techchallengepiechart.model;

import java.util.List;

public class MCourse {

    private String courseName;
    private List<MAbility> abilities = null;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<MAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<MAbility> abilities) {
        this.abilities = abilities;
    }
}
