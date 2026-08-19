package Repository.DB.impl;

import Model.ApstraktniDomenskiObjekat;
import Repository.DB.DbConnectionFactory;
import Repository.DB.DbRepository;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
            upit += ";";
        }

        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();

        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES(" + param.vratiVrednostiZaUbacivanje() + ");";
        System.out.println(upit);
        Statement s = DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(upit);
        s.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc() + ";";
        System.out.println(upit);
        Statement s = DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(upit);
        s.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc() + ";";
        System.out.println(upit);
        Statement s = DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(upit);
        s.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int addAndReturnId(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES(" + param.vratiVrednostiZaUbacivanje() + ");";
        System.out.println(upit);
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generisaniId = rs.getInt(1);
                    return generisaniId;
                }
            }
        }
        return -1;
    }
}