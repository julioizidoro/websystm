/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Produtosorcamento;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class ProdutoOrcamentoDao {
    
    private EntityManager manager;
    
    public Produtosorcamento salvar(Produtosorcamento produto) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        produto = manager.merge(produto);
        //fechando uma transação
        manager.getTransaction().commit();
        return produto;
    }
    
    public List<Produtosorcamento> listarProdutosOrcamento(String descricao) throws SQLException{
        manager = ConectionFactory.getConnection();
          manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Produtosorcamento p  where p.descricao like '" + descricao + "%' order by p.descricao" );
        manager.getTransaction().commit();
        return q.getResultList();
    }
    
    public Produtosorcamento consultar(int idProdutoOrcamento) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Produtosorcamento p where p.idprodutosOrcamento=" + idProdutoOrcamento + " order by p.descricao" );
         manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Produtosorcamento) q.getResultList().get(0);
        }
        return null;
    }
}
