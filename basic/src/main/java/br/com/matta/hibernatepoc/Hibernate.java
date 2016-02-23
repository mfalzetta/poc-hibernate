package br.com.matta.hibernatepoc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Marlon on 12/12/2014.
 */
public class Hibernate {

    SessionFactory sessionFactory;
    Session session;

    Hibernate()throws Exception{
        super();
        setUp();
    }

    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    public void save(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save( new Person("Pessoa", "11122233344") );
        session.getTransaction().commit();
        session.close();
    }

    public void listPerson(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Person> result = session.createQuery( "from Person" ).list();
        for ( Person person : (List<Person>) result ) {
            System.out.println(person.toString());
        }
        session.getTransaction().commit();
        session.close();
    }
}
