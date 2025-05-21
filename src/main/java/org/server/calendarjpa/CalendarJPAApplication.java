package org.server.calendarjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CalendarJPAApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarJPAApplication.class, args);
    }

}
