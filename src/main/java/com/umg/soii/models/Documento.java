package com.umg.soii.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Transactional
@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @Column(name = "id_doc")
    private String idDoc;

    @Column(name = "id_propietario")
    private int propietario;

    @Column(name = "fecha_doc_elab")
    private Date fechaDoc;

    @Column(name = "doc_guardado")
    private String docGuardado;
}
