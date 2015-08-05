package br.com.travelmate.facade;

import br.com.travelmate.dao.ValorSeguroDao;
import br.com.travelmate.model.Valoresseguro;
import java.sql.SQLException;
import java.util.List;


public class ValorSeguroFacade {
    private ValorSeguroDao valorSeguroDao;

    
    public Valoresseguro salvar(Valoresseguro valor) throws SQLException{
        valorSeguroDao= new ValorSeguroDao();
        return valorSeguroDao.salvar(valor);
    }
    public Valoresseguro consultar(int idvalor) throws SQLException{
        valorSeguroDao= new ValorSeguroDao();
        return valorSeguroDao.consultar(idvalor);
    }
    
    public List<Valoresseguro> listar(int idFornecedor) throws SQLException{
        valorSeguroDao= new ValorSeguroDao();
        return valorSeguroDao.listar(idFornecedor);
    }
}
