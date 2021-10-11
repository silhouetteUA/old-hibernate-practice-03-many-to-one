package com.mycompany.demo;


import com.mycompany.entities.Course;
import com.mycompany.entities.Instructor;
import com.mycompany.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

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
            int theID =11;
            Course course = session.get(Course.class, theID);
            session.delete(course);
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
