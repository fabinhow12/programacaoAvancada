/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
@XStreamAlias("itens")
public class ItensVenda {
    
    private int quantidade;
    private Produto produto;
    private double subTotal;

    public ItensVenda(int quantidade,Produto produto ) {
        this.quantidade = quantidade;        
        this.produto = produto;
        this.subTotal = this.quantidade * this.produto.getPrecoVenda();
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
}
