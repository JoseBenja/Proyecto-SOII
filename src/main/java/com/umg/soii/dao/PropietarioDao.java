package com.umg.soii.dao;

import com.umg.soii.models.Propietario;

import java.util.List;

public interface PropietarioDao {
    void agregarPropietario(Propietario propietario);

    List<Propietario> verPropietario(Propietario propietario);
}
