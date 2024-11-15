package com.dao.in_memory;

public class DaoPatternDbDemo {
    public static void main(String[] args) {
        DeveloperDao developerDao = new DbDeveloperDao(); // Developers data structure create

        // use the code from the DaoPatternDemo class
        // add the data
        developerDao.add(new Developer(0,"Ada"));  // Developer: Id 0, name: Ada added
        developerDao.add(new Developer(1,"Rob"));  // Developer: Id 1, name: Rob added

        // print all developers
        for (Developer developer : developerDao.findAll()) {
            System.out.println(  // Developer: [Id 0, Name : Ada ]
                    developer);  // Developer: [Id 1, Name : Rob ]
        }

        // find developer by id
        developerDao.findById(0);  // Developer: Id 0, found
        developerDao.findById(10); // Developer: Id 10, not found

        // update developer data
        Developer developer = developerDao.findById(0); // Developer: Id 0, found
        developer.setName("Adelaida");
        developerDao.update(developer); // Developer: Id 0, updated

        //delete the developer
        developerDao.deleteById(0); // Developer: Id 0, deleted
        developerDao.deleteById(10); // Developer: Id 10 not found
        developerDao.findById(0); // Developer: Id 0, not found
    }

}

