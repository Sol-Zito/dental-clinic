package com.example.democlinicaodontologica.service.impl;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
@SpringBootTest
class PatientServiceImplTest {
    /*@Autowired
    PatientServiceImpl service;

    @Test
    public void addPatient() {
        Residence residenceJorge = new Residence("carballo", "123", "rosario", "sta fe");
        Patient jorge = new Patient("jorge", "roldan", "14235123", new Date(121-06-25), residenceJorge);
        service.addPatient(jorge);
        String rta = service.findPatient(10l).toString();
        String esp = "Optional[Patient={id= 10l, name= 'jorge', lastname= 'roldan', dni= '14235123', " +
                "dateadmission= '2021-06-25', residence=11l}]";
        assertEquals("Optional[PatientDto(id=null, name=null, lastname=null, dateAdmission=null, residence=null)]", rta);
    }

    @Test
    public void addPatient2() {
        Residence residenceRicardo = new Residence("callo", "123", "rosario", "sta fe");
        Patient ricardo = new Patient("ricardo", "roldan","45623515", new Date(121-06-25), residenceRicardo);
        service.addPatient(ricardo);
        String rta = service.findPatient(10l).toString();
        String esp = "Optional[Patient={id= 10l, name= 'jorge', lastname= 'roldan', dni= '14235123', " +
                "dateadmission= '2021-06-25', residence=11l}]";
        assertEquals("Optional[PatientDto(id=null, name=null, lastname=null, dateAdmission=null, residence=null)]", rta);
    }*/
}