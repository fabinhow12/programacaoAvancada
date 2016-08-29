/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import dados.ItensVenda;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class ListaItensVenda {
    
    public static final ArrayList<ItensVenda> listaItensVenda = new ArrayList<>();
    
    
    public static void adicionaItem(ItensVenda item){
        listaItensVenda.add(item); 
    } 
    
    public static void removeItem(ItensVenda item) {
        listaItensVenda.remove(item); 
    }
    
    public static void limpaLista(){
        listaItensVenda.clear();
    }
    
    public static double valorTotal(){
        double valor = 0;
        
        for (ItensVenda itensVenda : listaItensVenda) {
           double get = itensVenda.getSubTotal();
           valor += get;
        }
        
        
        
        return valor;
    }

    
   
}
