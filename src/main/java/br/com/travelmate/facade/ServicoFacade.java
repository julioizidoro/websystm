/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.Interface.Servico;
import br.com.travelmate.dao.ServicoDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class ServicoFacade {
    
    private ServicoDao servicoDao;
    
    public Servico salvar(Servico servico) throws SQLException{
        servicoDao = new ServicoDao();
        return servicoDao.salvar(servico);
    }
    
    public List<Servico> lista(String sql) throws SQLException{
        servicoDao= new ServicoDao();
        return servicoDao.lista(sql);
    }
    
}
