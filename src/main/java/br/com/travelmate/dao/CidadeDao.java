package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Cidade;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CidadeDao {
    
    private EntityManager manager;
    
    public Cidade salvar(Cidade cidade) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        cidade = manager.merge(cidade);
        //fechando uma transação
        manager.getTransaction().commit();
        return cidade;
    }
    
    public List<Cidade> listar(int idPais) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Cidade c where c.pais.idpais=" + idPais + " order by p.nome");
        List<Cidade> listaCidade = q.getResultList();
        manager.getTransaction().commit();
        return listaCidade;
    }
}
