import javax.persistence.*;  
import javax.persistence.Id;  
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.GeneratedValue; 
import java.util.List;
import java.util.ArrayList;
 
@Entity
@Table(name = "role")
public class Role {

    @Id 
    @GeneratedValue 
    @Column(name="id")
    protected int id;

    @Column(name = "name")
    protected String name;

    @ManyToMany(targetEntity = Employee.class, cascade = {CascadeType.ALL})
    private List<Employee> employees = new ArrayList<Employee>();
     
    public Role() {
    }
   
    public Role(String name) { 
        this.name = name;
    }
 
    public Role(int id, String name) { 
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployee() {
        return employees;
    }
     
    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Role> getDefaultRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("Trainer"));
        roles.add(new Role("Trainee"));
        roles.add(new Role("Manager"));
        return roles;

    }

    
}