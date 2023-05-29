package com.umg.soii.repository;

import com.umg.soii.dao.DocumentoDao;
import com.umg.soii.models.Documento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public String descargarDocumento(Documento documento) {
        String query = "FROM Documento WHERE propietario= :prop";
        List<Documento> lista = entityManager.createQuery(query)
                .setParameter("prop", documento.getPropietario())
                .getResultList();


        return lista.get(0).getDocGuardado();
    }

}
