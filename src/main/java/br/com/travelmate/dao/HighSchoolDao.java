package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Highschool;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HighSchoolDao {
    
    public Highschool salvar(Highschool highschool) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        highschool = manager.merge(highschool);
        manager.getTransaction().commit();
        return highschool;
    }
    
    public List<Highschool> listar(String nome) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select h from Highschool h order by h.dataInicio");
        List<Highschool> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}
