package com.apache.fop;


import org.apache.xmlgraphics.util.UnitConv;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class BarcodeGeneration {

    private static final double HEIGHT_IN_MM = 8.5;
    private static final int DEFAULT_DPI = 150;
    private static final String BITMAP_CONTENT_TYPE = "image/x-png";

    public Code128Bean getCode128Bean(){
        Code128Bean code128Bean = new Code128Bean();
        code128Bean.setModuleWidth(UnitConv.in2mm(2.5f / DEFAULT_DPI));
        code128Bean.doQuietZone(false);
        code128Bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        code128Bean.setBarHeight(HEIGHT_IN_MM);
        return code128Bean;
    }
    public String generateEncodeBarcode(final String barcodeData, final Code128Bean code128Bean) throws IOException{
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                outputStream,
                BITMAP_CONTENT_TYPE,
                DEFAULT_DPI,
                BufferedImage.TYPE_BYTE_BINARY,
                false,
                0
        );
        code128Bean.generateBarcode(canvas, barcodeData);
        canvas.finish();
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
