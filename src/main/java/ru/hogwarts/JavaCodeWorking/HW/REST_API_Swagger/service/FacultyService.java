package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long id = 0L;

    public Faculty createFaculty(Faculty faculty) {
        if (faculty.getName() == null || faculty.getName().isBlank() || faculty.getColor() == null || faculty.getColor().isBlank() ) {
            return null;
        }
        faculty.setId(++id);
        return faculties.put(faculty.getId(), faculty);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty updateFaculty(long id, Faculty faculty) {
        if (faculty.getName() == null || faculty.getName().isBlank() || faculty.getColor() == null || faculty.getColor().isBlank() ) {
            return null;
        }
        faculty.setId(id);
        return faculties.put(id, faculty);
    }

    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        if (color == null || color.isBlank() ) {
            return null;
        }
        return faculties.values()
                .stream()
                .filter(f -> f.getColor().equals(color))
                .toList();
    }
}