package mx.qbits.plank.api.service;

import java.awt.image.BufferedImage;

public interface QRService {
    BufferedImage generateQRCodeImage(String barcodeText) throws Exception;
}
