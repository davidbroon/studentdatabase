package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @RequestMapping(method=RequestMethod.GET, value="/students")
    public Iterable<Student> student() {
        return studentRepo.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/students")
    public Student save(@RequestBody Student student) {
        studentRepo.save(student);

        return student;
    }

    @RequestMapping(method=RequestMethod.GET, value="/students/{id}")
    public Optional<Student> show(@PathVariable String id) {
        return studentRepo.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/students/{id}")
    public Student update(@PathVariable String id, @RequestBody Student student) {
        Optional<Student> optstudent = studentRepo.findById(id);
        Student c = optstudent.get();
        if(student.getName() != null)
            c.setName(student.getName());
        if(student.getAddress() != null)
            c.setAddress(student.getAddress());
        if(student.getCity() != null)
            c.setCity(student.getCity());
        if(student.getPhone() != null)
            c.setPhone(student.getPhone());
        if(student.getEmail() != null)
            c.setEmail(student.getEmail());
        studentRepo.save(c);
        return c;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/students/{id}")
    public String delete(@PathVariable String id) {
        Optional<Student> optstudent = studentRepo.findById(id);
        Student student = optstudent.get();
        studentRepo.delete(student);

        return "";
    }
}