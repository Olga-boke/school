package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Calling method addFaculty");

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.debug("Calling method findFaculty(facultyId={})",id);

        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Calling method editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Calling method deleteFaculty(facultyId={})",id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColorOrAndName(String colorOrName){
        logger.debug("Calling method findByColorOrName (colorOrName = {})", colorOrName);
        return facultyRepository.findByColorIgnoreCaseOrAndNameIgnoreCase(colorOrName, colorOrName);
    }
    public Collection<Student> getStudentsByFaculty(long id) {
        logger.debug("Calling method getStudentsByFaculty(facultyId={})",id);
        Faculty faculty = findFaculty(id);
        return studentRepository.findAllByFaculty_Id(faculty.getId());
    }
}
