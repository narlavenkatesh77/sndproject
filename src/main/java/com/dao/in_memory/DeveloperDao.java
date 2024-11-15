package com.dao.in_memory;

import java.util.List;

public interface DeveloperDao {
    List<Developer> findAll();
    Developer findById(int id);
    void add(Developer developer);
    void update(Developer developer);
    void deleteById(int id);
}