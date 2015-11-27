/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Notificacaoemail;
import br.com.travelmate.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class NotificacaoEmailDao {
    
    private EntityManager manager;
    
    public Notificacaoemail salvar(Notificacaoemail notificacaoemail) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        notificacaoemail = manager.merge(notificacaoemail);
        //fechando uma transação
        manager.getTransaction().commit();
        return notificacaoemail;
    }
    
    public List<Notificacaoemail> listarPorProduto(int idProduto) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select n from Notificacaoemail n where n.produtos.idprodutos=" + idProduto + " order by n.usuario.nome");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
}
