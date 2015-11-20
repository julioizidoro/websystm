/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.Interface.Tiposervico;
import br.com.travelmate.dao.TipoServicoDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class TipoServicoFacade {
    
    private TipoServicoDao tipoServicoDao;
    
    public Tiposervico salvar(Tiposervico tiposervico) throws SQLException{
        tipoServicoDao = new TipoServicoDao();
        return tipoServicoDao.salvar(tiposervico);
    }
    
    public List<Tiposervico> lista() throws SQLException{
        tipoServicoDao = new TipoServicoDao();
        return tipoServicoDao.lista();
    }
    
}
