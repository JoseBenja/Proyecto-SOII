package com.umg.soii.repository;

import com.umg.soii.dao.DocumentoDao;
import com.umg.soii.models.Documento;
import com.umg.soii.models.Propietario;
import com.umg.soii.models.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DocumentoRepository implements DocumentoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void guardarDocumento(Documento documento) {
        entityManager.merge(documento);
    }

    @Override
    public List<Documento> descargarDocumento(Documento documento) {
        String query = "FROM Documento WHERE id_doc = '" + documento.getIdDoc() + "'";

        return entityManager.createQuery(query).getResultList();
    }

}
