package mx.qbits.plank.api.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import mx.qbits.plank.api.service.QRService;

@RestController
@Api(value = "admin")
@RequestMapping("/api")
public class BarcodesController {    
    private QRService qrService;
    
    public BarcodesController(QRService qrService) {
        this.qrService = qrService;
    }
    
    @GetMapping(value = "/qr/{barcode}",  produces = "image/jpg")
    public @ResponseBody byte[] generateQRCodeImage(@PathVariable("barcode") String barcode) throws Exception  {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        BufferedImage img = qrService.generateQRCodeImage(barcode);
        ImageIO.write(img, "jpg", bao);
        return bao.toByteArray();
    }
}
