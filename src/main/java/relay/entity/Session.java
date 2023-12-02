package relay.entity;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import relay.data_access.StorageSingleton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private List<AttendanceRecord> attendance;
    private String classID;
    private Instructor instructor;
    private LocalDateTime startedAt;
    private String alphaNumericCode;
    private Image qrCodeImage;

    private static final String QR_DATA = "google.com";
    private static final int QR_SIZE_X = 100;
    private static final int QR_SIZE_Y = 100;

    public Session(List<AttendanceRecord> attendance, String classID, Instructor instructor,
                   LocalDateTime startedAt, String alphaNumericCode) throws UnsupportedEncodingException {
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

    public Image getQrCodeImage() {
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

    public void setQrCodeImage(Image qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public void generateQRCode() {
        try {
            String apiUrl = String.format("https://api.qrserver.com/v1/create-qr-code/?data=%s&size=%dx%d",
                    URLEncoder.encode(QR_DATA, "UTF-8"), QR_SIZE_X, QR_SIZE_Y);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Image qrCodeImage = ImageIO.read(connection.getInputStream());

                File outputfile = new File("QRCodeNew.png");
                ImageIO.write((RenderedImage) qrCodeImage, "png", outputfile);

                uploadImageToFirebaseStorage(outputfile);
            } else {
                throw new RuntimeException("Failed to fetch QR code image from the API");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadImageToFirebaseStorage(File file) {
        try {
            Bucket bucket = StorageSingleton.get();
            String fileName = "QRCodeNew.png";
            Blob blob = bucket.create(fileName, Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static boolean checkIfImageExists(String fileName) throws IOException {
        Bucket bucket = StorageSingleton.get();
        // List files in the Firebase Storage bucket
        Page<Blob> blobs = bucket.list();

        // Iterate through the files to check if the desired file exists
        for (Blob blob : blobs.iterateAll()) {
            if (blob.getName().equals(fileName)) {
                System.out.println("File exists in Firebase Storage.");
                return true;
            }
        }

        System.out.println("File not found in Firebase Storage.");
        return false;
    }
    public static void deleteFile(String fileName) {
        Bucket bucket = StorageSingleton.get();
        bucket.get(fileName).delete();
    }
}
