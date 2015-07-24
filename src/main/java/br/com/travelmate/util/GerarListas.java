/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.util;

import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.facade.PacoteTrechoFacade;
import br.com.travelmate.facade.PacotesFacade;
import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Produtos;
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
    
    public static List<Fornecedorcidade> listarFornecedorCidade(int idProduto, int idCidade){
        String sql = "SELECT f From Fornecedorcidade f where f.produtos.idprodutos=" + idProduto
                    + " and f.cidade.idcidade=" + idCidade + " order by f.fornecedor.nome";
        FornecedorCidadeFacade fornecedorCidadeFacede = new FornecedorCidadeFacade();
        List<Fornecedorcidade> listafornecedor = fornecedorCidadeFacede.listar(sql);
        if (listafornecedor==null){
            listafornecedor = new ArrayList<Fornecedorcidade>();
        }
        return listafornecedor;
    }
    
    public static List<Produtos> listarProdutos(String nome){
        ProdutoFacade produtoFacade = new ProdutoFacade();
        List<Produtos> lista = produtoFacade.listarProdutos(nome);
        if (lista==null){
            lista = new ArrayList<Produtos>();
        }
        return lista;
    }
}
