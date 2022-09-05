import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
public class Role {
    int id;
    String name;
    List<Employee> employees = new LinkedList <Employee>();

    public Role(int id, String name ) {
         this.id = id;
         this.name = name;
    }
    public Role(String name) {
        this.name = name;
    }
    
    public Role() {
        
    }
    public int getId() {
         return id;
    }
    public void setId(int id) {
        this.id = id;
    }	
    public String getName() {
         return name;
    }
    public void setName(String name) {
        this.name = name;
    }	
 
    public List<Employee> getEmployees() {
         return employees;
    }
    public void setEmployees(List<Employee> employees) {
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