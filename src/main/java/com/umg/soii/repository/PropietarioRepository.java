package com.umg.soii.repository;

import com.umg.soii.controllers.UsuarioController;
import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Propietario;
import com.umg.soii.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class PropietarioRepository implements PropietarioDao {



    private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void agregarPropietario(Propietario propietario) {
        entityManager.merge(propietario);

        logger.log(Level.INFO,"Propietario agregado con exito");
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
