/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.model.Usuario;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Kamila Rodrigues
 */
@Named
@ViewScoped
public class ProdutoUsuarioMB implements Serializable{
    
    
    private List<Usuario> listaUsuarioEmail;
    
    
    @PostConstruct
    public void init(){
         String sqlUsuario = "Select u from Usuario u order by u.nome";
        listaUsuarioEmail = GerarListas.listarUsuarios(sqlUsuario);
    }

    public List<Usuario> getListaUsuarioEmail() {
        return listaUsuarioEmail;
    }

    public void setListaUsuarioEmail(List<Usuario> listaUsuarioEmail) {
        this.listaUsuarioEmail = listaUsuarioEmail;
    }

}
