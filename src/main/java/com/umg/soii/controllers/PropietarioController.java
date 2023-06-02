package com.umg.soii.controllers;

import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Propietario;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class PropietarioController {

    private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private PropietarioDao propietarioDao;

    @RequestMapping(value = "api/agregarPropietario", method = RequestMethod.POST)
    public void agregarPropietario(@RequestBody Propietario propietario){
        logger.log(Level.INFO,"Datos del propietario recibidos");
        propietarioDao.agregarPropietario(propietario);
    }

    @RequestMapping(value = "api/verPropietario", method = RequestMethod.POST)
    public List<Propietario> verPropietario(@RequestBody Propietario propietario) {
        return propietarioDao.verPropietario(propietario);
    }
}
