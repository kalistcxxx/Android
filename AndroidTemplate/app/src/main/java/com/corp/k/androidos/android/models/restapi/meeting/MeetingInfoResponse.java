package com.corp.k.androidos.android.models.restapi.meeting;

import com.corp.k.androidos.android.models.restapi.SimpleResponse;

import java.util.List;

/**
 */

public class MeetingInfoResponse extends SimpleResponse {
    private List<MeetingInfo> meetings;

    public MeetingInfoResponse(List<MeetingInfo> meetings) {
        this.meetings = meetings;
    }

    public List<MeetingInfo> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingInfo> meetings) {
        this.meetings = meetings;
    }
}
