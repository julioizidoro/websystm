/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorlogo;
import java.sql.SQLException;
import javax.persistence.EntityManager;

/**
 *
 * @author Kamila Rodrigues
 */
public class FornecedorLogoDao {
    
     private EntityManager manager;
    
    public Fornecedorlogo salvar(Fornecedorlogo fornecedorlogo) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        fornecedorlogo = manager.merge(fornecedorlogo);
        manager.getTransaction().commit();
        return fornecedorlogo;
    }
    
    public Fornecedorlogo consultar(int idFornecedorLogo) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Fornecedorlogo fornecedorlogo = manager.find(Fornecedorlogo.class, idFornecedorLogo);
        manager.getTransaction().commit();
        return fornecedorlogo;
    }
    
}
