package com.spring.datajpa.app.models.dao;

import com.spring.datajpa.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDaoImpl implements IClienteDao{

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente); // El metodo merge actualiza un registro ya existente.
        }else{
            em.persist(cliente); // Guarda el objeto cliente
        }
    }

    @Override
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

}
