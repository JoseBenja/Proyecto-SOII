package com.umg.soii.controllers;

import com.umg.soii.dao.UsuarioDao;
import com.umg.soii.models.Usuario;
import com.umg.soii.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogged = usuarioDao.obtenerUsuarioPorCredencial(usuario);
        if (usuarioLogged != null) {
            return jwtUtil.create(String.valueOf(usuarioLogged.getIdUser()), usuarioLogged.getNomUser());
        }
        return "Fail";
    }
}
