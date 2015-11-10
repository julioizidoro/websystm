/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.travelmate.model.Cliente;


/**
 *
 * @author Wolverine
 */
@FacesConverter(value="ClienteConverter")
public class ClienteConverter  implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Cliente> listaCliente = (List<Cliente>) component.getAttributes().get("listaCliente");
        if (listaCliente != null) {
            for (Cliente cliente : listaCliente) {
                if (cliente.getNome().equalsIgnoreCase(value)) {
                    return cliente;
                }
            }
        } else {
            return new Cliente();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")){
            return "Selecione";
        }else {
            if (value instanceof Cliente){
                Cliente cliente = (Cliente) value;
                 return cliente.getNome();
            }else return "";
        }
    }
    
}
