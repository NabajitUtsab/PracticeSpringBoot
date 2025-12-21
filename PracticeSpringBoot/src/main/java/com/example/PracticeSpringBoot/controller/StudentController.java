package com.example.PracticeSpringBoot.controller;

import com.example.PracticeSpringBoot.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<Student>();
    Long nextId = 1L;

    //get all products
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //creating
    @PostMapping
    public ResponseEntity<List<Student>> createStudent(@RequestBody Student studentObj) {
        studentObj.setId(nextId++);
        students.add(studentObj);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //search by id
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return new ResponseEntity<>(students.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    //PathVariable vs RequestParam
//    @GetMapping("{id}")
//    public ResponseEntity<String> pathVsReqParam(@PathVariable Long id, @RequestParam String keyword) {
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getId().equals(id)) {
//                String s = students.get(i).getName() + keyword;
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            }
//        }
//
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }


    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student studentObj) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, studentObj);
                return new ResponseEntity<>(studentObj, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Id not founded", HttpStatus.NOT_FOUND);
    }

}
