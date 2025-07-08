package helperDB;

import lombok.Getter;

@Getter
public class Ticket {
    private int id;
    private int capacity;
    private int participantCount;

    public Ticket(int id, int capacity, int participantCount) {
        this.id = id;
        this.capacity = capacity;
        this.participantCount = participantCount;
    }

}
