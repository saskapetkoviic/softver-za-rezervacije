package Kontroleri;

import Forme.PrikazRezervacijeForma;
import Forme.modeli.ModelTabeleRezervacije;
import Komunikacija.Komunikacija;
import Model.ApstraktniDomenskiObjekat;
import Model.Rezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class PrikazRezervacijaController {
    private PrikazRezervacijeForma forma;

    public PrikazRezervacijaController(PrikazRezervacijeForma forma) {
        this.forma = forma;
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniTabelu();
        forma.setVisible(true);
    }

    public void napuniTabelu() {
        try {
            Rezervacija r = new Rezervacija();
            r.setStatus("");
            List<ApstraktniDomenskiObjekat> rezervacije = Komunikacija.getInstance().pretraziRezervaciju(r);
            ModelTabeleRezervacije model = new ModelTabeleRezervacije(rezervacije);
            forma.getTblRezervacije().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
        }
    }

 private void pretrazi() {
        try {
            String kriterijum = forma.getTxtPretraga().getText();
            Rezervacija r = new Rezervacija();
            r.setStatus(kriterijum);
            List<ApstraktniDomenskiObjekat> rezultat = Komunikacija.getInstance().pretraziRezervaciju(r);
            ModelTabeleRezervacije model = new ModelTabeleRezervacije(rezultat);
            forma.getTblRezervacije().setModel(model);

            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe rezervacije po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao rezervacije po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe rezervacije po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
 
        private void addActionsListener() {
            forma.getBtnPretrazi().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });

        forma.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblRezervacije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Izaberite rezervaciju iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleRezervacije model = (ModelTabeleRezervacije) forma.getTblRezervacije().getModel();
                Rezervacija r = model.vratiRezervaciju(red);
                Koordinator.Koordinator.getInstance().otvoriIzmeniRezervaciju(r);
            }
        });
    }
}