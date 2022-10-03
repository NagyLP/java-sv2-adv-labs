package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(length = 245, nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private ActivityType type;

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public Activity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
