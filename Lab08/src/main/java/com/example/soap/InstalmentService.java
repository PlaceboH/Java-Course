package com.example.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.example.models.Instalment;
import com.example.models.Instalment;

@WebService
public interface InstalmentService {
    @WebMethod
	List<Instalment> getAllInstalments();
    @WebMethod
    int createNewInstalment(Instalment instalment);
    @WebMethod
    int deleteInstalment(@WebParam(name="id") @XmlElement(required=true) long id);
    @WebMethod
    int updateInstalment(@WebParam(name="id") @XmlElement(required=true) long id, Instalment instalment);
}
