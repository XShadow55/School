package ru.hogwarts.school.service.impl;

import ch.qos.logback.classic.spi.ConfiguratorRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.repository.StudentRepository;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private String  avatarsDir = "C://Users/lopat/IdeaProjects/school/src/main/resources/static";


    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }
    // Поиск аватара по его id
    @Override
    public Avatar findAvatar(Long id) {
        logger.info("Was invoked method for create avatar");
        return avatarRepository.findById(id).orElse(new Avatar());
    }
// загрузка аватара для студента
    @Override
    public void uploadAvatar(Long studentId, @org.jetbrains.annotations.NotNull MultipartFile avatarFile) throws IOException {
        logger.info("The method was called to load avatars for students");
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    // Вызов расширения аватара у студента
    private String getExtensions(String fileName) {
        logger.info("A method was called to get the extension of avatars");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
// получает всех аватаров
    @Override
    public List<Avatar> findAll(Integer number, Integer size) {
        logger.info("A method was called to get all avatars");
        PageRequest pageRequest = PageRequest.of(number,size);
        return avatarRepository.findAll(pageRequest).getContent();
    }

}
