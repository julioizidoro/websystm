/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Fornecedorcomissaocurso;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class FornecedorComissaoCursoDao {
    
    private EntityManager manager;
    
    public Fornecedorcomissaocurso salvar(Fornecedorcomissaocurso fornecedorcomissaocurso) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        fornecedorcomissaocurso = manager.merge(fornecedorcomissaocurso);
        //fechando uma transação
        manager.getTransaction().commit();
        return fornecedorcomissaocurso;
    }
    
    
     public List<Fornecedorcomissaocurso> listar(int idFornecedor, int idPais) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select f from Fornecedorcomissaocurso f where f.fornecedor.idfornecedor=" + idFornecedor + " and f.pais.idpais=" + idPais);
        List<Fornecedorcomissaocurso> listaFornecedor = q.getResultList();
        manager.getTransaction().commit();
        return listaFornecedor;
    }
}
