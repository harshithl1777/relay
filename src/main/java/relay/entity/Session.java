package relay.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private List<AttendanceRecord> attendance;
    private String classID;
    private Instructor instructor;
    private LocalDateTime startedAt;
    private String alphaNumericCode;
    private String qrCodeImage;

    public Session(List<AttendanceRecord> attendance, String classID, Instructor instructor,
                   LocalDateTime startedAt, String alphaNumericCode) {
        this.attendance = attendance;
        this.classID = classID;
        this.instructor = instructor;
        this.startedAt = startedAt;
        this.alphaNumericCode = alphaNumericCode;
    }

    public List<AttendanceRecord> getAttendance() {
        return attendance;
    }

    public String getClassID() {
        return classID;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public String getAlphaNumericCode() {
        return alphaNumericCode;
    }

    public String getQrCodeImage() {
        return qrCodeImage;
    }

    public void setAttendance(List<AttendanceRecord> attendance) {
        this.attendance = attendance;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setAlphanumCode(String alphanumCode) {
        this.alphaNumericCode = alphanumCode;
    }

    public void setQrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public void generateQRCode() {
        // TODO: Add in API call to QR Code API to generate QR code
    }
}
