/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Parcelamentopagamento;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class ParcelamentoPagamentoDao {
    
    private EntityManager manager;
    
    public Parcelamentopagamento salvar(Parcelamentopagamento parcelamentopagamento) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        parcelamentopagamento = manager.merge(parcelamentopagamento);
        //fechando uma transação
        manager.getTransaction().commit();
        return parcelamentopagamento;
    }
    
     public void excluir(int idParcelamentopagamento) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Parcelamentopagamento passagem = manager.find(Parcelamentopagamento.class, idParcelamentopagamento);
        manager.remove(passagem);
        //fechando uma transação
        manager.getTransaction().commit();
    }
     
     public List<Parcelamentopagamento> listar(int idFormaPagamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Parcelamentopagamento p where p.idformapagamento=" + idFormaPagamento);
        List<Parcelamentopagamento> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
}
