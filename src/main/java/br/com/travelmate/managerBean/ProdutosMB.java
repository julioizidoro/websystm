package br.com.travelmate.managerBean;


import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.model.Usuario;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ProdutosMB implements Serializable{
    
    private Produtos produtos;
    private List<Produtos> listaProdutos;
    private List<Usuario> listaUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        String sql = "Select u from Usuario u where u.cargo like 'gerente%' order by u.nome";
        listaUsuario = GerarListas.listarUsuarios(sql);
       
        if(produtos==null){
            usuario = new Usuario();
            produtos = new Produtos();
        }else 
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

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public String cadastrarProdutos(){
        produtos = new Produtos();
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
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    public String voltar(){
        return "consProdutos";
    }
    
    public String salvarProduto(){
         produtos.setIdgerente(usuario.getIdusuario());
         ProdutoFacade produtoFacade = new ProdutoFacade();
         produtos = produtoFacade.salvar(produtos);
         FacesMessage mensagem = new FacesMessage("Salvo com Sucesso! ", "Produto salvo.");
         FacesContext.getCurrentInstance().addMessage(null, mensagem);
         produtos = new Produtos();
         RequestContext.getCurrentInstance().closeDialog(null);
         return "";
    }
    
    
}
