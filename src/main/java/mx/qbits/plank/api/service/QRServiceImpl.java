package mx.qbits.plank.api.service;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRServiceImpl implements QRService {
    private Logger logger = LoggerFactory.getLogger(QRServiceImpl.class);
    
    @Override
    public BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        logger.info("Processing: " + barcodeText);
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
