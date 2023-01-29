package com.apache.fop;

import com.apache.fop.domain.Sample;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class JAXBContextUtil {
    public String createJaxbContext(Sample sample) throws JAXBException{
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Sample.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(sample, stringWriter);
        return stringWriter.toString();
    }
}
