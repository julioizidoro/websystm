/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.converter;

import br.com.travelmate.model.Cambio;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Wolverine
 */
@FacesConverter(value="CambioConverter")
public class CambioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Cambio> listaCambio = (List<Cambio>) component.getAttributes().get("listaCambio");
        if (listaCambio != null) {
            for (Cambio cambio : listaCambio) {
                if (cambio.getMoedas().getSigla().equalsIgnoreCase(value)) {
                    return cambio;
                }
            }
        } else {
            return new Cambio();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")){
            return "Selecione";
        }else {
            Cambio cambio = (Cambio) value;
            return cambio.getMoedas().getSigla();
        }
    }
    
}
