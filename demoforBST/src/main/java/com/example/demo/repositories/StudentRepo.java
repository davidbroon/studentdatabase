package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, String> {
    @Override
    void delete(Student deleted);
}