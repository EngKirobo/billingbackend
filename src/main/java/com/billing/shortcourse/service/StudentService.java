package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.StudentDTO;
import com.billing.shortcourse.entity.Student;
import com.billing.shortcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(StudentDTO dto) {
        Student student = new Student();
        mapDtoToEntity(dto, student);
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, StudentDTO dto) {
        Student student = getStudentById(id);
        mapDtoToEntity(dto, student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private void mapDtoToEntity(StudentDTO dto, Student student) {
        student.setName(dto.getName());
        student.setAdmino(dto.getAdmino());
        student.setEmail(dto.getEmail());
        student.setCountry(dto.getCountry());
        student.setDob(dto.getDob());
        student.setGenderId(dto.getGenderId());
        student.setEntryId(dto.getEntryId());
        student.setProgramId(dto.getProgramId());
        student.setIntakeId(dto.getIntakeId());
        student.setTelephone(dto.getTelephone());
    }
}