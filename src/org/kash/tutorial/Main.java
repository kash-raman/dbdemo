package org.kash.tutorial;

import org.kash.tutorial.service.PersonDao;
import org.kash.tutorial.service.PersonDaoJDBCImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static PersonDao personDao;

    public static void main(String[] args) {
        learnJDBC();
        learnSpringJDBC();
    }

    private static void learnSpringJDBC() {
        System.out.println("Spring Jdbc..");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-data-context.xml");
        PersonDao personDao = applicationContext.getBean("personDaoSpringJdbcImpl",PersonDao.class);
        System.out.println( personDao.getAllPerson());
        System.out.println(personDao.getPerson(1));
        System.out.println(personDao.getCount());
    }

    private static void learnJDBC() {
        System.out.println("Jdbc..");
        PersonDao personDao =  new PersonDaoJDBCImpl();
        System.out.println(personDao.getCount());
        System.out.println(personDao.getPerson(2));
        System.out.println(personDao.getAllPerson());
    }


}
