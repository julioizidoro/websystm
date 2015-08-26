/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Filtroorcamentoproduto;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class FiltroOrcamentoProdutoDao {
    
    private EntityManager manager;
    
    public Filtroorcamentoproduto salvar(Filtroorcamentoproduto filtro) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        filtro = manager.merge(filtro);
        //fechando uma transação
        manager.getTransaction().commit();
        return filtro;
    }
    
    public void excluir(int idFiltro) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Filtroorcamentoproduto filtro = manager.find(Filtroorcamentoproduto.class, idFiltro);
        manager.remove(filtro);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
    public Filtroorcamentoproduto pesquisar(int idProduto, int idProdutoOrcamento) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select f from Filtroorcamentoproduto f where f.produtosorcamento=" + idProdutoOrcamento  + 
                " and f.produtos=" + idProduto);
           manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Filtroorcamentoproduto) q.getResultList().get(0);
        }
        return null;
    }
    
    public List<Filtroorcamentoproduto> pesquisar(int idProduto) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + idProduto + " order by f.produtosorcamento.descricao");
           manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
    
}
