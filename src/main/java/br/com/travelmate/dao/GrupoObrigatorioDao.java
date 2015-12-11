package br.com.travelmate.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Grupoobrigatorio;

public class GrupoObrigatorioDao {
	
private EntityManager manager;
    
    public Grupoobrigatorio salvar(Grupoobrigatorio grupoobrigatorio) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo
        manager.getTransaction().begin();
        grupoobrigatorio = manager.merge(grupoobrigatorio);
        //fechando
        manager.getTransaction().commit();
        return grupoobrigatorio;
    }
    
    
    public List<Grupoobrigatorio> listar(String sql)throws SQLException{
         manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Grupoobrigatorio> lista = null;
        if (q.getResultList().size()>0){
            lista =  q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
    
    public Grupoobrigatorio consultar(int idcoprodutos) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("SELECT g FROM Grupoobrigatorio g where g.coprodutos.idcoprodutos=" + idcoprodutos);
         manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Grupoobrigatorio) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    

}
