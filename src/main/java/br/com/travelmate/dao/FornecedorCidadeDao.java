/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorcidade;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class FornecedorCidadeDao {
    
    private EntityManager manager;
    
    public Fornecedorcidade salvar(Fornecedorcidade fornecedorcidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedorcidade = manager.merge(fornecedorcidade);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedorcidade;
    }
    
    public List<Fornecedorcidade> listar(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Fornecedorcidade> listaFornecedorCidade = q.getResultList();
        manager.getTransaction().commit();
        return listaFornecedorCidade;
    }
    
    public Fornecedorcidade getFonecedorCidade(int idFornecedor, int idCidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select f from Fornecedorcidade f where f.fornecedor.idfornecedor=" + idFornecedor + " and f.cidade.idcidade=" + idCidade);
        Fornecedorcidade fornecedorcidade = null;
        if (q.getResultList().size()>0){
            fornecedorcidade = (Fornecedorcidade) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return fornecedorcidade;
    }
    
    public Fornecedorcidade getFornecedorCidade(int idFornecedorCidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Fornecedorcidade fornecedorcidade = manager.find(Fornecedorcidade.class, idFornecedorCidade);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedorcidade;
    }
    
    
    public Fornecedorcidade getFonecedorCidade(int idCodigo) throws SQLException{
        if (idCodigo > 0) {
            manager = ConectionFactory.getConnection();
            manager.getTransaction().begin();

            Query q = manager.createQuery("Select f from Fornecedorcidade f where f.codigo=" + idCodigo);
            Fornecedorcidade fornecedorcidade = null;
            if (q.getResultList().size() > 0) {
                fornecedorcidade = (Fornecedorcidade) q.getResultList().get(0);
            }
            manager.getTransaction().commit();
            return fornecedorcidade;
        }
        return null;
    }
}
