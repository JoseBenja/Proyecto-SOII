package com.umg.soii.repository;

import com.umg.soii.dao.UsuarioDao;
import com.umg.soii.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioRepository implements UsuarioDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Usuario obtenerUsuarioPorCredencial(Usuario usuario) {
        String query = "FROM Usuario WHERE nomUser = :nomUser";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("nomUser", usuario.getNomUser())
                .getResultList();

        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }
}
