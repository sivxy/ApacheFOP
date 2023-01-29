package com.apache.fop;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

public class XsltURIResolver implements URIResolver {
    @Override
    public Source resolve(String href, String base) throws TransformerException{
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("" + href);
        return new StreamSource(inputStream);
    }
}
