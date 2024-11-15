package com.dao.in_memory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDeveloperDao implements DeveloperDao {

    // list is working as a data source
    private final List<Developer> developers;

    public InMemoryDeveloperDao() {
        this.developers = new ArrayList<>();
        System.out.println("Developers data structure created");
    }

    @Override
    public List<Developer> findAll() {
        return new ArrayList<>(developers);
    }

    @Override
    public Developer findById(int id) {
        Developer found = findByIdInternal(id);
        if (found == null) {
            System.out.println("Developer: Id " + id + ", not found");
            return null;
        }
        System.out.println("Developer: Id " + id + ", found");
        return new Developer(found.getId(), found.getName());
    }

    @Override
    public void add(Developer developer) {
        developers.add(developer);
        System.out.println("Developer: Id " + developer.getId() +
                ", name: " + developer.getName() + " added");
    }

    @Override
    public void update(Developer developer) {
        Developer found = findByIdInternal(developer.getId());
        if (found != null) {
            found.setName(developer.getName());
            System.out.println("Developer: Id " + developer.getId() + ", updated");
        } else {
            System.out.println("Developer: Id " + developer.getId() + ", not found");
        }
    }

    @Override
    public void deleteById(int id) {
        Developer found = findByIdInternal(id);
        if (found != null) {
            developers.remove(found);
            System.out.println("Developer: Id " + id + ", deleted");
        } else {
            System.out.println("Developer: Id " + id + ", not found");
        }
    }

    private Developer findByIdInternal(int id) {
        for (Developer developer : developers) {
            if (id == developer.getId()) {
                return developer;
            }
        }

        return null;
    }
}