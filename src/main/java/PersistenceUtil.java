import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static final String PERSISTENT_UNIT_NAME = "myPersistenceUnit";
    private static final EntityManagerFactory EMF;
    static {
        try {
            EMF = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        } catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }
    public static EntityManager getEm() {
        return EMF.createEntityManager();
    }
}

