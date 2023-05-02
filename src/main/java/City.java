import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;
    @Column(name = "city_name")
    private String city_name;

    @OneToMany(mappedBy = "city", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Employee> employee;


    public City(String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public City() {

    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return  "ID города: " + city_id + " Город: " + city_name;
    }
}
