package com.spring.datajpa.app.models.dao;

import com.spring.datajpa.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

   List<Cliente> findAll();

   void save(Cliente cliente);

   public Cliente findOne(Long id);

   void delete(Long id);
}
