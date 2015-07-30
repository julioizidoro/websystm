/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PaisProdutoDao {
    
    private EntityManager manager;
    
    public Paisproduto salvar(Paisproduto paisProduto) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        paisProduto = manager.merge(paisProduto);
        //fechando uma transação
        manager.getTransaction().commit();
        return paisProduto;
    }
    
    public List<Paisproduto> listar(int idProduto) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Paisproduto p where p.produtos.idprodutos=" + idProduto + " order by p.pais.nome");
        List<Paisproduto> listaPaisProduto = q.getResultList();
        manager.getTransaction().commit();
        return listaPaisProduto;
    }
}
