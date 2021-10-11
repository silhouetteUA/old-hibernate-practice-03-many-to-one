package com.mycompany.demo;


import com.mycompany.entities.Course;
import com.mycompany.entities.Instructor;
import com.mycompany.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .addAnnotatedClass(Course.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();
            int theID =1;
            Instructor instructor = session.get(Instructor.class,theID);
            Course course1 = new Course("Spring 5 - step by step guide");
            Course course2 = new Course("Java Object Oriented Programming, best practices");
            instructor.addCourse(course1);
            course2.setInstructor(instructor);
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
