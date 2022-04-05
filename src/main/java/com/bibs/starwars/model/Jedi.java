package com.bibs.starwars.model;

public class Jedi {

    private int id;
    private String name;
    private int strength;
    private String version;

    public Jedi(){

    }

    public Jedi(int id, String name, int strength, String version) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.version = version;
    }

    public Jedi (String name, int strength){
        this.name = name;
        this.strength = strength;
    }
}
