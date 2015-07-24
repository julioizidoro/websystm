/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.util;

import br.com.travelmate.facade.PacoteTrechoFacade;
import br.com.travelmate.facade.PacotesFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Unidadenegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class GerarListas {
    
    public static List<Unidadenegocio> listarUnidade(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        return unidadeNegocioFacade.listar();
    }
    
    public static List<Pacotes> listarPacotes(String sql){
        PacotesFacade pacotesFacade= new PacotesFacade();
        List listaPacotes = pacotesFacade.consultar(sql);
        if (listaPacotes==null){
           listaPacotes = new ArrayList<Pacotes>();
        }
        return listaPacotes;
    }
    
    public static List<Pacotetrecho> listarPacoteTrecho(String sql){
        PacoteTrechoFacade pacoteTrechoFacade = new PacoteTrechoFacade();
        List<Pacotetrecho> lista = listarPacoteTrecho(sql);
        if (lista==null){
            lista = new ArrayList<Pacotetrecho>();
        }
        return lista;
    }
    
}
