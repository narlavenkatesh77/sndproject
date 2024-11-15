package com.dao.in_memory;

public class Developer {

    private String name;
    private int id;

    public Developer( int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


        // constructor
        // getters and setters

        @Override
        public String toString() {
            return "Developer: [Id " + id
                    + ", Name : " + name + " ]";
        }
    }

