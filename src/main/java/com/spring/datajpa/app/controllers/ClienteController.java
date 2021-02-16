package com.spring.datajpa.app.controllers;

import com.spring.datajpa.app.models.dao.IClienteDao;
import com.spring.datajpa.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente") // guarda en la sesion los atributos del objeot cliente mapeado al formulario
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model){

        Cliente cliente = null;

        if (id > 0){
            cliente = clienteDao.findOne(id);

        }else {
            return "Redirect:/listar";
        }

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);

        return "form";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){ // Seddionstatus para eliminar la sesion

        if (result.hasErrors()){

            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        clienteDao.save(cliente);
        status.setComplete(); // para eliminar el objeto cliente de la session
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String delete(@PathVariable Long id){

        if (id > 0) {
            clienteDao.delete(id);
        }
        return "redirect:/listar";
    }

}
