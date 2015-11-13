/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Ladies;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LadiesDao {
    
    public Ladies salvar(Ladies ladies) throws Exception{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        ladies = manager.merge(ladies);
        manager.getTransaction().commit();
        return ladies;
    }
    
    public List<Ladies> listar(String nome) throws Exception{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select l from Ladies l order by l.vendas.dataVenda ");
        List<Ladies> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}