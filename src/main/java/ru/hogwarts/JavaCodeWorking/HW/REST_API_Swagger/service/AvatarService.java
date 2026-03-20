package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Avatar;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model.Student;
import ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDirectory;

    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public ResponseEntity<?> uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.badRequest().body("Student with id \"" + studentId + "\" was not found");
        }

        Path filePath = Path.of(avatarsDirectory, student + "." + getExtention(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);


        try (InputStream io = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(io, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }


        Avatar avatar = findAvatar(studentId);
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);
        return ResponseEntity.ok().build();

    }

    public String getExtention(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long studentId) {
        Optional<Avatar> opt = avatarRepository.findByStudentId(studentId);
        if (opt.isEmpty()) {
            return new Avatar();
        }
        return opt.get();
    }
}