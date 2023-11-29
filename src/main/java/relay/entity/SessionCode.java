package relay.entity;

public class SessionCode {
	private String alphanumCode;
	private String qrCodeImage;

	public SessionCode(String code) {
		this.alphanumCode = code;
	}

	public void generateQRCode() {
		// TODO: Add in API call to QR Code API to generate QR code
	}

	public String getAlphanumCode() {
		return alphanumCode;
	}

	public String getQrCodeImage() {
		return qrCodeImage;
	}

	public void setAlphanumCode(String alphanumCode) {
		this.alphanumCode = alphanumCode;
	}

	public void setQrCodeImage(String qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
	}
}
