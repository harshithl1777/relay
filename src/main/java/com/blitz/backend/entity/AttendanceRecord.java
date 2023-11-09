package com.blitz.backend.entity;
import java.time.LocalDateTime;

public class AttendanceRecord {
    String studentEmailAddress;
    String studentIdentifier;
    LocalDateTime createdAt;

    public static void sendEmailConfirmation(){
    }

    public static void sendRecordApproval(){
    }

    public static void sendRecordDenial(){
    }

}