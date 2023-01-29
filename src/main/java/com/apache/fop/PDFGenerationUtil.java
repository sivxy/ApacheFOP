package com.apache.fop;

import org.apache.fop.apps.*;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

public class PDFGenerationUtil {

    XsltURIResolver xsltURIResolver;
    public Result createResult(Fop fop) throws FOPException{
        return new SAXResult(fop.getDefaultHandler());
    }

    public StreamSource readXmlInput(String xmlString){
        StringReader stringReader = new StringReader(xmlString);
        return new StreamSource(stringReader);
    }

    public Fop getFop(ByteArrayOutputStream pdfOutStream) throws FOPException, URISyntaxException{
        FopFactory fopFactory = FopFactory.newInstance(new URI("."));
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        return fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfOutStream);
    }

    public Transformer getTransformer(InputStream xslInputStream) throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
        transformerFactory.setURIResolver(xsltURIResolver);
        return transformerFactory.newTransformer(new StreamSource(xslInputStream));
    }
}
