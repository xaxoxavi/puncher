package com.esliceu.puncher.parser.model;


import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "AULA")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classroom {

    @XmlAttribute(name="codi")
    private Integer codi;

    @XmlAttribute(name="descripcio")
    private String descripcio;

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
