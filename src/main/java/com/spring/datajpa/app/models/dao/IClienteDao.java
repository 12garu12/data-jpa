package com.spring.datajpa.app.models.dao;

import com.spring.datajpa.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

   List<Cliente> findAll();

}
