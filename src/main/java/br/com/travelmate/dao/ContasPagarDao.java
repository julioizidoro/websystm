/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Contaspagar;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Julio
 */
public class ContasPagarDao {
    
    private EntityManager manager;
    
    public Contaspagar salvar(Contaspagar conta) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        conta = manager.merge(conta);
        manager.getTransaction().commit();
        return conta;
    }
    
}
