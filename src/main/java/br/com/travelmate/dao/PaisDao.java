package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Pais;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Wolverine
 */
public class PaisDao {
    
    private EntityManager manager;
    
    public Pais salvar(Pais pais) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pais = manager.merge(pais);
        //fechando uma transação
        manager.getTransaction().commit();
        return pais;
    }
    
    public List<Pais> listar(String nome) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Pais p where p.nome like '%" + nome + "%' order by p.nome");
        List<Pais> listaPais = q.getResultList();
        manager.getTransaction().commit();
        return listaPais;
    }
}
