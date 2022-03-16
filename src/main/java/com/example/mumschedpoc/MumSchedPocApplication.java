package com.example.mumschedpoc;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.entities.enums.UserRole;
import com.example.mumschedpoc.repositories.IUserRepository;
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
        User admin1 = new User(null, "Ricardo", UserRole.ADMIN, "rianelli@miu.edu", passwordEncoder.encode("12345"));
        User admin2 = new User(null, "Uriel", UserRole.ADMIN, "ubattanoli@miu.edu", passwordEncoder.encode("12345"));
        User student = new User(null, "Lebap", UserRole.STUDENT, "lebap@miu.edu", passwordEncoder.encode("12345"));
        User faculty = new User(null, "Emdad", UserRole.ADMIN, "emdad@miu.edu", passwordEncoder.encode("12345"));

        userRepository.saveAll(Arrays.asList(admin1, admin2, student, faculty));
    }
}
