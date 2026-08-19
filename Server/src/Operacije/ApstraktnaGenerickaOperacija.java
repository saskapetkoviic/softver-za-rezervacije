package Operacije;

import Repository.DB.DbRepository;
import Repository.DB.impl.DbRepositoryGeneric;
import Repository.Repository;

public abstract class ApstraktnaGenerickaOperacija {
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object obj, String kljuc) throws Exception {
        try {
            preduslovi(obj);
            zapocniTransakciju();
            izvrsiOperaciju(obj, kljuc);
            potvrdiTransakciju();
        } catch (Exception ex) {
            ponistiTransakciju();
            throw ex;
        }
    }

    protected abstract void preduslovi(Object obj) throws Exception;

    protected abstract void izvrsiOperaciju(Object obj, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DbRepository) broker).rollback();
    }
}