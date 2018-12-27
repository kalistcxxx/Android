package com.corp.k.androidos.android.models.restapi.meeting;

/**
 */

public class MeetingInfo {
    private String id;
    private String title;
    private String room;
    private String date;
    private String startTime;
    private String endTime;
    private String speaker;
    private String category;
    private String participants;
    private boolean isRegistered;

    public MeetingInfo() {
    }

    public MeetingInfo(String id, String title, String room, String date, String startTime, String endTime, String speaker, String category, String participants, boolean isRegistered) {
        this.id = id;
        this.title = title;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
        this.category = category;
        this.participants = participants;
        this.isRegistered = isRegistered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
