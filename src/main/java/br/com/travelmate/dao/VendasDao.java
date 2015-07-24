/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Vendas;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class VendasDao {
    
    private EntityManager manager;
    
    public Vendas salvar(Vendas venda) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        venda = manager.merge(venda);
        //fechando uma transação
        manager.getTransaction().commit();
        return venda;
    }
    
    public Vendas consultarVendas(int idVenda) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Vendas venda = manager.find(Vendas.class, idVenda);
        //fechando uma transação
        manager.getTransaction().commit();
        return venda;
    }
    
   
    
    
    
    
    
   
    public void excluir(int idVenda) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Vendas venda = manager.find(Vendas.class, idVenda);
        manager.remove(venda);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
    
    
    public Vendas vendaCliente(int idCliente) throws SQLException{
        
        manager = ConectionFactory.getConnection();
          manager.getTransaction().begin();
        Query q = manager.createQuery("Select v from Vendas v where v.cliente=" + idCliente);
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Vendas) q.getResultList().get(0);
        }
        return null;
    }
    
    public List<Vendas> lista() throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("Select v from Vendas v where v.fornecedorcidade.idfornecedorcidade=1");
         manager.getTransaction().commit();
        return q.getResultList();
    }
    
    
    
}
