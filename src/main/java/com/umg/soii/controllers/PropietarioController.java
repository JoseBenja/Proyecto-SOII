package com.umg.soii.controllers;

import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Propietario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class PropietarioController {

    @Autowired
    private PropietarioDao propietarioDao;

    @RequestMapping(value = "api/agregarPropietario", method = RequestMethod.POST)
    public void agregarPropietario(@RequestBody Propietario propietario){
        propietarioDao.agregarPropietario(propietario);
    }

    @RequestMapping(value = "api/verPropietario", method = RequestMethod.POST)
    public List<Propietario> verPropietario(@RequestBody Propietario propietario) {
        return propietarioDao.verPropietario(propietario);
    }
}
