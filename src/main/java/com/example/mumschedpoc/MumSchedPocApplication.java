package com.example.mumschedpoc;

import com.example.mumschedpoc.entities.*;
import com.example.mumschedpoc.entities.enums.UserRole;
import com.example.mumschedpoc.repositories.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MumSchedPocApplication implements CommandLineRunner {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICourseRepository courseRepository;

    @Autowired
    IBlockRepository blockRepository;

    @Autowired
    IBlockCourseRepository blockCourseRepository;

    @Autowired
    IStudentInformationRepository studentInformationRepository;

    @Autowired
    IStudentBlockRepository studentBlockRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MumSchedPocApplication.class, args);
    }

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    public Info apiInfo() {
        Info info = new Info();
        info
                .title("MUMSched API")
                .description("MUMSched Open API")
                .version("v0.0.1");
        return info;
    }

    @Override
    public void run(String... args) throws Exception {

        Course course1 = new Course(null, "CS390", "FPP", "Learn basics of Java and Algorithms");
        Course course2 = new Course(null, "CS401", "MPP", "Learn basics about Software Engineering and Functional Programming");
        Course course3 = new Course(null, "CS425", "Software Engineering", "Learn more about Software Engineering");
        course2.addPreRequisite(course1);
        courseRepository.saveAll(Arrays.asList(course1, course2, course3));


        User admin1 = new User(null, "Ricardo", UserRole.ADMIN, "rianelli@miu.edu", passwordEncoder.encode("12345"));
        User admin2 = new User(null, "Uriel", UserRole.ADMIN, "ubattanoli@miu.edu", passwordEncoder.encode("12345"));
        User student = new User(null, "Lebap", UserRole.STUDENT, "lebap@miu.edu", passwordEncoder.encode("12345"));
        User faculty = new User(null, "Emdad", UserRole.FACULTY, "emdad@miu.edu", passwordEncoder.encode("12345"));
        faculty.getFacultyInformation().setPreferredCourses(Arrays.asList(course1, course3));
        userRepository.saveAll(Arrays.asList(admin1, admin2, student, faculty));


        Block block1 = new Block(null);
        Block block2 = new Block(null);
        blockRepository.saveAll(Arrays.asList(block1, block2));

        BlockCourse blockCourse1 = new BlockCourse(null, block1, course3, faculty, 30);
        BlockCourse blockCourse2 = new BlockCourse(null, block2, course1, faculty, 24);
        BlockCourse blockCourse3 = new BlockCourse(null, block1, course2, faculty, 12);
        blockCourseRepository.saveAll(Arrays.asList(blockCourse1, blockCourse2, blockCourse3));

        StudentInformation stu1 = student.getStudentInformation();

        StudentBlock studentBlock1 = new StudentBlock(null, block1.getId(), stu1);
        StudentBlock studentBlock2 = new StudentBlock(null, block2.getId(), stu1);
        studentBlockRepository.saveAll(Arrays.asList(studentBlock1, studentBlock2));

        stu1.setStudentBlocks(Arrays.asList(studentBlock1, studentBlock2));
        studentInformationRepository.save(stu1);

        BlockCoursePriority coursePriority1 = new BlockCoursePriority(null, 1, blockCourse1, studentBlock1);
        BlockCoursePriority coursePriority2 = new BlockCoursePriority(null, 2, blockCourse3, studentBlock1);
        studentBlock1.setCoursePriorities(Arrays.asList(coursePriority1, coursePriority2));
        studentInformationRepository.save(stu1);
    }
}
