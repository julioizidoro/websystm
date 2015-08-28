package br.com.travelmate.managerBean;

import br.com.travelmate.model.Produtos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class produtosMB implements Serializable{
    
    private Produtos produtos;
    private List<Produtos> listaProdutos;
    
    @PostConstruct
    public void init(){
        if(produtos==null){
            produtos = new Produtos();
        }
        if(listaProdutos==null){
            listaProdutos = new ArrayList<Produtos>();
        }
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
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
    
    public String cancelarCadastro(){
        RequestContext.getCurrentInstance().closeDialog("null");
        return "";
    }
    
    public String voltar(){
        return "consProdutos";
    }
}
