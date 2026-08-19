package Kontroleri;

import Forme.PrikazGostijuForma;
import Forme.modeli.ModelTabeleGosti;
import Komunikacija.Komunikacija;
import Koordinator.Koordinator;
import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class PrikazGostijuController {
    private PrikazGostijuForma forma;

    public PrikazGostijuController(PrikazGostijuForma forma) {
        this.forma = forma;
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniTabelu();
        forma.setVisible(true);
    }

    public void napuniTabelu() {
        try {
            List<ApstraktniDomenskiObjekat> gosti = Komunikacija.getInstance().ucitajGoste();
            ModelTabeleGosti model = new ModelTabeleGosti(gosti);
            forma.getTblGosti().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
        }
    }

private void pretrazi() {
        try {
            String kriterijum = forma.getTxtPretraga().getText();
            Gost g = new Gost();
            g.setIme(kriterijum);
            List<ApstraktniDomenskiObjekat> rezultat = Komunikacija.getInstance().pretraziGosta(g);
            ModelTabeleGosti model = new ModelTabeleGosti(rezultat);
            forma.getTblGosti().setModel(model);

            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe goste po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao goste po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe goste po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionsListener() {
        forma.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriDetaljiGost(null);
            }
        });

        forma.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblGosti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Izaberite gosta iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleGosti model = (ModelTabeleGosti) forma.getTblGosti().getModel();
                Gost gost = model.vratiGosta(red);
                Koordinator.getInstance().otvoriDetaljiGost(gost);
            }
        });

        forma.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblGosti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Izaberite gosta iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    ModelTabeleGosti model = (ModelTabeleGosti) forma.getTblGosti().getModel();
                    Gost gost = model.vratiGosta(red);
                    Komunikacija.getInstance().obrisiGosta(gost);
                    model.removeAt(red);
                    JOptionPane.showMessageDialog(forma, "Sistem je obrisao gosta.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne može da obriše gosta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        forma.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });
    }
}