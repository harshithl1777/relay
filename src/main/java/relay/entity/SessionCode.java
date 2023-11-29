package relay.entity;

public class SessionCode {
	private String code;
	private String qrCodeImage;

	public SessionCode(String code) {
		this.code = code;
	}

	public void generateQRCode() {
		// API call to generate QR code
	}

	public String getQrCodeImage() {
		return qrCodeImage;
	}

	public void setQrCodeImage(String qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
	}
}
