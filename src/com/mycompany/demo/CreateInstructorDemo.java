package com.mycompany.demo;


import com.mycompany.entities.Course;
import com.mycompany.entities.Instructor;
import com.mycompany.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .addAnnotatedClass(Course.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("Evgeniy", "Dmitriev","edmitr@pe.lifecell.com.ua");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/channel/edmitr","Football");
            tempInstructor.setInstructorDetailId(tempInstructorDetail);
            session.beginTransaction();
            System.out.println("Saving instructor: "+ tempInstructor.toString());
            session.save(tempInstructor);
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
