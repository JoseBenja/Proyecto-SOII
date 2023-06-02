package com.umg.soii.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Transactional
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private int idUser;

    @Column(name = "nom_user")
    private String nomUser;

    @Column(name = "pass_user")
    private String pass;

    @Column(name = "area")
    private String area;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "perfil")
    private String perfil;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private boolean estado;
}
