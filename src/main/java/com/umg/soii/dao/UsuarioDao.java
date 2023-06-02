package com.umg.soii.dao;

import com.umg.soii.models.Usuario;

public interface UsuarioDao {

    Usuario obtenerUsuarioPorCredencial(Usuario usuario);
}
