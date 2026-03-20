package ru.hogwarts.JavaCodeWorking.HW.REST_API_Swagger.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    private String filePath;
    private Long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;

    public Avatar(Long id, String filePath, Long fileSize, String mediaType) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
    }

    public Avatar() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Avatar avatar)) return false;
        return Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(fileSize, avatar.fileSize) && Objects.equals(mediaType, avatar.mediaType) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, student);
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "Student=" + student +
                ", mediaType='" + mediaType + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", id=" + id +
                '}';
    }
}