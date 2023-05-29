package com.umg.soii.repository;

import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Propietario;
import com.umg.soii.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PropietarioRepository implements PropietarioDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void agregarPropietario(Propietario propietario) {
        entityManager.merge(propietario);
    }

    @Override
    public List<Propietario> verPropietario(Propietario propietario) {
        String query = "FROM Propietario WHERE estado = :estado";

        List<Propietario> lista = entityManager.createQuery(query)
                .setParameter("estado", propietario.isEstado())
                .getResultList();

        return lista;
    }
}
