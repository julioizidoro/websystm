/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorcomissaocursoproduto;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Wolverine
 */
public class FornecedorComissaoCursoProdutoDao {
    
    private EntityManager manager;
    
    public Fornecedorcomissaocursoproduto salvar(Fornecedorcomissaocursoproduto fornecedor) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedor = manager.merge(fornecedor);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedor;
    }
    
    public List<Fornecedorcomissaocursoproduto> listar(int idFornecedorcomissaocurso, int idprodutoorcamento) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select f from Fornecedorcomissaocursoproduto f where f.fornecedorcomissaocurso.idfornecedorcomissao=" + idFornecedorcomissaocurso + " and f.produtosorcamento.idprodutosOrcamento=" + idprodutoorcamento);
        List<Fornecedorcomissaocursoproduto> listaFornecedor = q.getResultList();
        manager.getTransaction().commit();
        return listaFornecedor;
    }
    
}
