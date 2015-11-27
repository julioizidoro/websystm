package br.com.travelmate.managerBean.produto;

import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.model.Produtos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ProdutosMB implements Serializable{
    
    private List<Produtos> listaProdutos;
    private String descricao="";
    
    @PostConstruct
    public void init(){
        gerarListaProdutos();
    }


    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String cadastrarProdutos(){
        RequestContext.getCurrentInstance().openDialog("cadProdutos");
        return "";
    }
    
    public String cadastrarUsuarios(){
        RequestContext.getCurrentInstance().openDialog("cadUsuariosProduto");
        return "";
    }
    
    public String consultarUsuarios(){
        return "consUsuariosProduto";
    }
    
    public String adicionarItens(){
        return "adicionarItem";
    }
    
    public String consultarSubProdutos(){
        return "consSubProdutos";
    }
    
    public String cadastrarSubProdutos(){
        RequestContext.getCurrentInstance().openDialog("cadSubProdutos");
        return "";
    }
   
    
    public String voltar(){
        return "consProdutos";
    }
    
    
    
    public void listarPesquisaPorDescricao(){
        ProdutoFacade produtoFacade = new ProdutoFacade();
        listaProdutos = produtoFacade.listarProdutos(descricao);
        if(listaProdutos==null){
            listaProdutos = new ArrayList<Produtos>();
        }
    }
    
    public void gerarListaProdutos(){
        ProdutoFacade produtoFacade = new ProdutoFacade();
        listaProdutos = produtoFacade.listarProdutos();
        if(listaProdutos==null){
            listaProdutos = new ArrayList<Produtos>();
        }
    }
    
    public void retornoDialogNovo(SelectEvent event){
       Produtos produtos = (Produtos) event.getObject();
       listaProdutos.add(produtos);
   }
}
