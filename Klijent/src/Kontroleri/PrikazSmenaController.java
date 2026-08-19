package Kontroleri;

import Forme.PrikazSmeneForma;
import Forme.modeli.ModelTabeleSmene;
import Komunikacija.Komunikacija;
import Model.ApstraktniDomenskiObjekat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PrikazSmenaController {
    private PrikazSmeneForma forma;

    public PrikazSmenaController(PrikazSmeneForma forma) {
        this.forma = forma;
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniTabelu();
        forma.setVisible(true);
    }

    public void napuniTabelu() {
        try {
            List<ApstraktniDomenskiObjekat> smene = Komunikacija.getInstance().pretraziSmenu();
            ModelTabeleSmene model = new ModelTabeleSmene(smene);
            forma.getTblSmene().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
        }
    }

    private void pretrazi() {
        try {
            List<ApstraktniDomenskiObjekat> smene = Komunikacija.getInstance().pretraziSmenu();
            String kriterijum = forma.getTxtPretraga().getText().toLowerCase();

            List<ApstraktniDomenskiObjekat> filtrirane = new ArrayList<>();
            for (ApstraktniDomenskiObjekat ado : smene) {
                Model.Smena s = (Model.Smena) ado;
                if (s.getVremePocetka().toString().toLowerCase().contains(kriterijum) ||
                    s.getVremeZavrsetka().toString().toLowerCase().contains(kriterijum)) {
                    filtrirane.add(s);
                }
            }

            ModelTabeleSmene model = new ModelTabeleSmene(filtrirane);
            forma.getTblSmene().setModel(model);

            if (filtrirane.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe smene po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao smene po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe smene po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionsListener() {
        forma.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });
    }
}