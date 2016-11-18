package eapcourse.domain;
 
import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity
public class SimpleProperty {
 
    @Id
    private String key;
    private String value;
 
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "SimpleProperty [key=" + key + ", value=" + value
                + "]";
    }
 
}
