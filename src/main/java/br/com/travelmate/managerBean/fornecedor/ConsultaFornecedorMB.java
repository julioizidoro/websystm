package br.com.travelmate.managerBean.fornecedor;

import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.facade.FornecedorFacade;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Produtos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class ConsultaFornecedorMB implements Serializable{
    
    private Fornecedorcidade fornecedorcidade;
    private List<Fornecedor> listaFornecedor;
    private Cidade cidade;
    private Produtos produto;
    private Fornecedor fornecedor;

    public ConsultaFornecedorMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        produto = (Produtos) session.getAttribute("produtos");
        cidade = (Cidade) session.getAttribute("cidade");
        session.removeAttribute("produtos");
        session.removeAttribute("cidade");
        gerarListaFornecedor();
        fornecedor = new Fornecedor();
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Produtos getProduto() {
        return produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    
    
    
    
    public void gerarListaFornecedor(){
        FornecedorFacade forncedorFacade = new FornecedorFacade();
        listaFornecedor = forncedorFacade.listar("SELECT f From Fornecedor f order by f.nome");
        if(listaFornecedor==null){
            listaFornecedor = new ArrayList<Fornecedor>();
        }
    }

    
    public String salvarFornecedor(){
        for(int i=0;i<listaFornecedor.size();i++){
            if(listaFornecedor.get(i).isSelecionado()){
                fornecedorcidade = new Fornecedorcidade();
                fornecedorcidade.setCidade(cidade);
                fornecedorcidade.setProdutos(produto);
                fornecedorcidade.setFornecedor(listaFornecedor.get(i));
                fornecedorcidade.setPeso(0);
                FornecedorCidadeFacade fornecedorCidadeFacade = new FornecedorCidadeFacade();
                fornecedorCidadeFacade.salvar(fornecedorcidade);
            }
        }
        RequestContext.getCurrentInstance().closeDialog(fornecedorcidade);
        return "";
    }
    
     public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
   
    public String novo(){
        RequestContext.getCurrentInstance().openDialog("cadFornecedor");
        return "";
    }
    
    public String salvar(){
        FornecedorFacade fornecedorFacade = new FornecedorFacade();
        fornecedor = fornecedorFacade.salvar(fornecedor);
        return "";
    }
}
