package com.esliceu.puncher.parser;


import com.esliceu.puncher.parser.model.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

@Component
public class XMLParser {

    @Autowired
    public XMLParser() {

    }

    public Center parse(ByteContainer item) {

        JAXBContext jaxbContext = null;
        Unmarshaller jaxbUnmarshaller = null;
        Center center = null;

        try {
            jaxbContext = JAXBContext.newInstance(Center.class);


            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            center = (Center) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(item.getBytes()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return center;
    }
}