package helperDB;

import lombok.Getter;

import java.sql.Timestamp;
@Getter
public class Session {
    private int id;
    private int webinarId;
    private Timestamp date;
    private int duration;
    private int order;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Session(int id, int webinarId, Timestamp date, int duration, int order, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.webinarId = webinarId;
        this.date = date;
        this.duration = duration;
        this.order = order;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
