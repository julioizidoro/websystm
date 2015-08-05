package br.com.travelmate.facade;

import br.com.travelmate.dao.SeguroViagemDao;
import br.com.travelmate.model.Seguroviagem;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeguroViagemFacade {
    
    SeguroViagemDao seguroViagemDao;
    
     public Seguroviagem salvar(Seguroviagem seguroViagem) throws SQLException{
        seguroViagemDao =new SeguroViagemDao();
        return seguroViagemDao.salvar(seguroViagem);
    }
    
    public Seguroviagem consultar(int idVenda) throws SQLException{
        seguroViagemDao = new SeguroViagemDao();
        return seguroViagemDao.consultar(idVenda);
    }
    
    public void excluir(int idSeguroViagem) throws SQLException{
        seguroViagemDao = new SeguroViagemDao();
        seguroViagemDao.excluir(idSeguroViagem);
    }
    
}
