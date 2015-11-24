/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Coeficientejuros;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class CoeficienteJurosDao {
    
    private EntityManager manager;
    
    public Coeficientejuros salvar(Coeficientejuros coeficientejuros) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        coeficientejuros = manager.merge(coeficientejuros);
        //fechando uma transação
        manager.getTransaction().commit();
        return coeficientejuros;
    }
    
    public Coeficientejuros consultar(int numeroParcelas) throws SQLException{
        manager = ConectionFactory.getConnection();
          manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Coeficientejuros c where c.numeroParcelas=" + numeroParcelas);
         manager.getTransaction().commit();
        return (Coeficientejuros) q.getSingleResult();
    }
    
    public List<Coeficientejuros> listar() throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Coeficientejuros c order by c.numeroParcelas");
         manager.getTransaction().commit();
        return q.getResultList();
    }
}
