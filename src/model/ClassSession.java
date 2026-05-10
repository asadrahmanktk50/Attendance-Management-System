package model;

import java.sql.Date;
import java.sql.Time;

public class ClassSession {
    private int id;
    private int sectionId;
    private Date date;
    private Time startTime;
    private Time endTime;

    public ClassSession(int id, int sectionId, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.sectionId = sectionId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() { return id; }
    public int getSectionId() { return sectionId; }
    public Date getDate() { return date; }
    public Time getStartTime() { return startTime; }
    public Time getEndTime() { return endTime; }
    @Override
    public String toString() {
        return "Date: " + date + " | Time: " + startTime;
    }
}