/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.bean;

import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Pacotecarro;
import br.com.travelmate.model.Pacotecruzeiro;
import br.com.travelmate.model.Pacotehotel;
import br.com.travelmate.model.Pacoteingresso;
import br.com.travelmate.model.Pacotepassagem;
import br.com.travelmate.model.Pacotepasseio;
import br.com.travelmate.model.Pacotetransfer;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pacotetrem;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class FinalizarPacoteOperadora {
    
    private List<Pacotetrecho> listaTrecho;
    private Cambio cambioPacote;
    private float valorGross;
    private float valorNet;

    public FinalizarPacoteOperadora(List<Pacotetrecho> listaTrecho, Cambio cambio) {
        this.listaTrecho = listaTrecho;
        this.cambioPacote = cambio;
        valorGross=0.0f;
        valorNet=0.0f;
    }
    
    private void iniciarCalculo(){
        for(int i=0;i<listaTrecho.size();i++){
            calcularValoresCarro(listaTrecho.get(i).getPacotecarroList());
            calcularValoresCruzeiro(listaTrecho.get(i).getPacotecruzeiroList());
            calcularValoresHotel(listaTrecho.get(i).getPacotehotelList());
            calcularValoresIngresso(listaTrecho.get(i).getPacoteingressoList());
            calcularValoresPassagem(listaTrecho.get(i).getPacotepassagemList());
            calcularValoresPasseio(listaTrecho.get(i).getPacotepasseioList());
            calcularValoresTransfer(listaTrecho.get(i).getPacotetransferList());
            calcularValoresTrem(listaTrecho.get(i).getPacotetremList());
        }
    }
    
    private void calcularValoresCarro(List<Pacotecarro> listaCarros){
        if (listaCarros!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<listaCarros.size();i++){
                if (listaCarros.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + listaCarros.get(i).getValorgross();
                    valornet  = valornet + listaCarros.get(i).getValornet();
                }else {
                    valorgross = converterCambio(listaCarros.get(i).getValorgross(), listaCarros.get(i).getCambio().getValor());
                    valornet = converterCambio(listaCarros.get(i).getValornet(), listaCarros.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresPassagem(List<Pacotepassagem> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
//                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
//                    valorgross = valorgross + lista.get(i).getValorgross();
//                    valornet  = valornet + lista.get(i).getValornet();
//                }else {
//                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
//                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
//                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresHotel(List<Pacotehotel> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresCruzeiro(List<Pacotecruzeiro> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresIngresso(List<Pacoteingresso> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresPasseio(List<Pacotepasseio> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresTransfer(List<Pacotetransfer> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    private void calcularValoresTrem(List<Pacotetrem> lista){
        if (lista!=null){
            float valorgross=0.0f;
            float valornet=0.0f;
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getCambio().getMoedas().getSigla().equalsIgnoreCase(cambioPacote.getMoedas().getSigla())){
                    valorgross = valorgross + lista.get(i).getValorgross();
                    valornet  = valornet + lista.get(i).getValornet();
                }else {
                    valorgross = converterCambio(lista.get(i).getValorgross(), lista.get(i).getCambio().getValor());
                    valornet = converterCambio(lista.get(i).getValornet(), lista.get(i).getCambio().getValor());
                }
            }
            valorGross= valorgross + valorGross;
            valorNet = valorNet + valornet;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public float converterCambio(float valor, float valorCambio){
        float valorBR = valor / valorCambio;
        float valorEUA = valorBR * cambioPacote.getValor();
        return valorEUA;
    }
}
