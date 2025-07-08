package helperDB;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static helperDB.CommonData.faker;

@Getter
public class Job {
    // (uuid, connection, queue, payload, exception, failed_at)
    private String uuid;
    private String connecction;
    private String queue;
    private String payload;
    private String exception;
    private Timestamp failed_at;
    public static List<Job> jobs;

    public Job(String uuid, String connecction, String queue, String payload, String exception, Timestamp failed_at) {
        this.uuid = uuid;
        this.connecction = connecction;
        this.queue = queue;
        this.payload = payload;
        this.exception = exception;
        this.failed_at = failed_at;
    }


    static String jobClass = "App\\Jobs\\ExampleJob";


    public List<Job> getJobFields() {
        return List.of(
                new Job(faker.internet().uuid(), faker.internet().slug(), faker.app().name() + "_queue", jobClass + new RuntimeException("Bir hata oluştu!"), " " + new RuntimeException("Bir hata oluştu!"), Timestamp.valueOf(LocalDateTime.now())),
                new Job(faker.internet().uuid(), faker.internet().slug(), faker.app().name() + "_queue", jobClass + new RuntimeException("Bir hata oluştu!"), " " + new RuntimeException("Bir hata oluştu!"), Timestamp.valueOf(LocalDateTime.now())),
                new Job(faker.internet().uuid(), faker.internet().slug(), faker.app().name() + "_queue", jobClass + new RuntimeException("Bir hata oluştu!"), " " + new RuntimeException("Bir hata oluştu!"), Timestamp.valueOf(LocalDateTime.now())),
                new Job(faker.internet().uuid(), faker.internet().slug(), faker.app().name() + "_queue", jobClass + new RuntimeException("Bir hata oluştu!"), " " + new RuntimeException("Bir hata oluştu!"), Timestamp.valueOf(LocalDateTime.now())),
                new Job(faker.internet().uuid(), faker.internet().slug(), faker.app().name() + "_queue", jobClass + new RuntimeException("Bir hata oluştu!"), " " + new RuntimeException("Bir hata oluştu!"), Timestamp.valueOf(LocalDateTime.now()))
        );
    }

    public static List<Job> generateFailedJob(int count) {
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Job field = new Job(
                    faker.internet().uuid(),
                    faker.internet().slug(),
                    faker.app().name() + "_queue",
                    jobClass + new RuntimeException("Bir hata oluştu!"),
                    " " + new RuntimeException("Bir hata oluştu!"),
                    Timestamp.valueOf(LocalDateTime.now())

            );
            jobs.add(field);
        }
        return jobs;
    }



}
