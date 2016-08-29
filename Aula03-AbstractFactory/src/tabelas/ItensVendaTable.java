/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

import dados.ItensVenda;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import repositorios.ListaItensVenda;

/**
 *
 * @author fabio
 */
public class ItensVendaTable extends AbstractTableModel {

    //Constantes 
    private final int CODIGO = 0;
    private final int NOME_PRODUTO = 1;
    private final int PRECO_UNIT = 2;
    private final int QUANTIDADE = 3;
    private final int SUB_TOTAL = 4;

    //vetores 
    private final ArrayList<ItensVenda> listaItens;
    private final String[] colunas = {"Código","Nome do produto", "Preço unitário", "Quantidade", "Sub-total"};

    public ItensVendaTable() {
        listaItens = ListaItensVenda.listaItensVenda;
    }
    
    public ItensVenda getItemVenda(int linha){
        ItensVenda get = null;
        if (linha == -1){
            return get;
        }else {
            get = listaItens.get(linha);
        }
        
           return get;
    }

    public void atualizarTabela() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.listaItens.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItensVenda item = this.listaItens.get(rowIndex); 
        
        switch (columnIndex){
            case CODIGO:
                return item.getProduto().getCodigo();
            case NOME_PRODUTO: 
                return item.getProduto().getNome(); 
            case PRECO_UNIT: 
                return item.getProduto().getPrecoVenda(); 
            case QUANTIDADE: 
                return item.getQuantidade();
            case SUB_TOTAL: 
                return item.getSubTotal();
            default: 
                return item; 
        }
        
        
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return this.colunas[columnIndex];
    }

}
