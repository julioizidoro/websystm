/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ocursoformapagamento;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Wolverine
 */
public class OCursoFormaPagamentoDao {
    
    private EntityManager manager;
    
    public Ocursoformapagamento salvar(Ocursoformapagamento formaPagamento) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        formaPagamento = manager.merge(formaPagamento);
        manager.getTransaction().commit();
        return formaPagamento;
    }
    
}
