/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;

import br.com.travelmate.model.Programasteens;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Greicieli
 */
public class ProgramasTeensDao {
       public Programasteens salvar(Programasteens programasteens) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        programasteens = manager.merge(programasteens);
        manager.getTransaction().commit();
        return programasteens;
    }
    
    public List<Programasteens> listar(String nome) throws SQLException{
        EntityManager 
                manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Programasteens p order by p.vendas.dataVenda");
        List<Programasteens> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}
