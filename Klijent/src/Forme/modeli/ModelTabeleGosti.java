package Forme.modeli;

import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleGosti extends AbstractTableModel {
    private List<ApstraktniDomenskiObjekat> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Telefon", "Mesto"};

    public ModelTabeleGosti(List<ApstraktniDomenskiObjekat> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gost gost = (Gost) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gost.getIdGost();
            case 1:
                return gost.getIme();
            case 2:
                return gost.getPrezime();
            case 3:
                return gost.getTelefon();
            case 4:
                return gost.getMesto().getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

    public Gost vratiGosta(int row) {
        return (Gost) lista.get(row);
    }

    public void removeAt(int row) {
        lista.remove(row);
        fireTableRowsDeleted(row, row);
    }
}