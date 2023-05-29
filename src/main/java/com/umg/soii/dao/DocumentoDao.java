package com.umg.soii.dao;

import com.umg.soii.models.Documento;

import java.util.List;

public interface DocumentoDao {

    void guardarDocumento(Documento documento);

    String descargarDocumento(Documento documento);
}
