import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class CityDAOIpl implements CityDao{
    Scanner scanner = new Scanner(System.in);
    @Override
    public List<City> getAllCity() {
        List<City> cities;
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        TypedQuery<City> query = manager.createQuery("SELECT s FROM City s", City.class);
        cities = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        cities.forEach(System.out::println);
        return cities;
    }

    @Override
    public City getCityByID(int id) {
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        City city = manager.find(City.class, id);
        manager.getTransaction().commit();
        manager.close();
        return city;
    }

    @Override
    public void createCity(City city) {
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        manager.persist(city);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void updateCity(City city) {
        System.out.println("Введите новое название города:");
        String cn = scanner.nextLine();
        city.setCity_name(cn);

        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        manager.merge(city);
        manager.getTransaction().commit();
        manager.close();
        System.out.println("Название города " + city + " обновлено.");
    }

    @Override
    public void deleteCity(City city) {
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        City city1 = manager.find(City.class, city.getCity_id());
        manager.remove(city1);
        manager.getTransaction().commit();
        System.out.println("Город " + city1 + " удалён из базы");
        manager.close();
    }
}
