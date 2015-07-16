/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;



import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Moedas;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class CambioDao {
    
    private EntityManager manager;
    
    public Cambio salvar(Cambio cambio) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        cambio = manager.merge(cambio);
        //fechando uma transação
        manager.getTransaction().commit();
        return cambio;
    }
    
    public void excluir(Cambio cambio) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        cambio = manager.find(Cambio.class, cambio.getIdcambio());
        manager.remove(cambio);
        //fechando uma transação
        manager.getTransaction().commit();
    }
    
    public Cambio consultar(int idCambio) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Cambio cambio = manager.find(Cambio.class, idCambio);
        //fechando uma transação
        manager.getTransaction().commit();
        return cambio;
    }
    
    
    
    public List<Moedas> listaMoedas() throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select m from Moedas m");
        if (q.getResultList().size()>0){
            manager.getTransaction().commit();
            return q.getResultList();
        }
        manager.getTransaction().commit();
        return null;
    }
    
    public Cambio consultarCambioMoeda(String data, int idMoeda) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Cambio c where c.moedas=" + idMoeda + " and c.data='" + data + "'");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Cambio) q.getResultList().get(0);
        }
        return null;
    }
    
    public Moedas consultarMoeda(int idMoeda) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Moedas moeda = manager.find(Moedas.class, idMoeda);
        //fechando uma transação
        manager.getTransaction().commit();
        return moeda;
    }
    
    public List<Cambio> listar(String data) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Cambio c where c.data='" + data + "'");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
    
}
