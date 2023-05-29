package com.umg.soii.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Transactional
@Entity(name = "Propietario")
@Table(name = "propietarios")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private int idPropietario;

    @Column(name = "nom_propietario", nullable = false)
    private String nomPropietario;

    @Column(name = "fecha_nac", nullable = false)
    private Date fechaNac;

    @Column(name = "estado")
    private boolean estado;
}
