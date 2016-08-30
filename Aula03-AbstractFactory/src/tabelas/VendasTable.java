/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

import dados.Venda;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import repositorios.RepositorioVenda;

/**
 *
 * @author alunofavip
 */
public class VendasTable extends AbstractTableModel {

    private static final int CODIGO = 0;
    private static final int CODFUNC = 1;
    private static final int CODCLIENT = 2;
    private static final int VALORTOTAL = 3;
    private static final int TIPOPAG = 4;

    private RepositorioVenda repositorio;

    private ArrayList<Venda> listaVendas;
    private final String[] colunas = {"Cod", "cod Func", "cod client", "valor total", "tipo pagamento"};

    public VendasTable() {
        repositorio = RepositorioVenda.getInstancia();
        listaVendas = repositorio.getVendas();
    }

    @Override
    public int getRowCount() {
        return listaVendas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda get = this.listaVendas.get(rowIndex);
        
        switch (columnIndex){
            
            case CODIGO:
                return get.getCodigo();
            case CODFUNC:
                return get.getFuncionario().getCodigo();
            case CODCLIENT:
                return get.getCliente().getCodigo();
            case VALORTOTAL:
                return get.getValorTotal();
            case TIPOPAG:
                return get.getTipoCartao();
            default:
                return null;
            
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return this.colunas[columnIndex];
    }

}
