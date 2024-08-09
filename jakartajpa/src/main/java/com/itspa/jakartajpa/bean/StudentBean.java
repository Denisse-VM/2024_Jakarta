package com.itspa.jakartajpa.bean;

import com.itspa.jakartajpa.entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 52443
 */
@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager entityManager;
    
    //injectar
    @EJB
    private StudentBean studentBean;
    
     //INSERTAR
    public void saveStudent(Student student) {        
        if (student.getStudentId() == null) {
            saveNewStudent(student);
        } else {
            updateStudent(student);
        }      
    }

    private void saveNewStudent(Student student) {
        entityManager.persist(student);
    }

    private void updateStudent(Student student) {
        entityManager.merge(student);
    }
    
    public Student getStudent(Long studentId) {
        Student student;

        student = entityManager.find(Student.class, studentId);

        return student;
    }
            
    public void deleteStudent(Student student) {
        entityManager.remove(student);
    }
    
    //MOSTRAR ESTUDIANTE
    public List<Student> getStudents(){//seleccionar el java.util.List
        List<Student> studentList = null;
        try{
            //jpql                                          =*              s<-alias
            Query query = entityManager.createQuery("select s from Student s", 
                    Student.class);
            studentList = query.getResultList();
        }catch(Exception e){}
        return studentList;
    }
    
   
}
