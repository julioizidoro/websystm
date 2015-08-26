package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Idioma;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class IdiomaDao {
    
     private EntityManager manager;
    
    public Idioma salvar(Idioma idioma) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        idioma = manager.merge(idioma);
        //fechando uma transação
        manager.getTransaction().commit();
        return idioma;
    }
    
    
    public List<Idioma> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Idioma> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
}
