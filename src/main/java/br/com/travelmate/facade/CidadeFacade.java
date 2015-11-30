package br.com.travelmate.facade;

import br.com.travelmate.dao.CidadeDao;
import br.com.travelmate.model.Cidade;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 

public class CidadeFacade {
    
    CidadeDao cidadeDao;
    
    public Cidade salvar(Cidade cidade) {
        cidadeDao = new CidadeDao();
        try {
            return cidadeDao.salvar(cidade);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Cidade> listar(int idPais) {
        cidadeDao = new CidadeDao();
        try {
            return cidadeDao.listar(idPais);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
