package kata6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

 
@XmlRootElement
@XmlAccessorType (XmlAccessType.NONE)
public class Dinosaur {
    public String name;
    public String description;

    public Dinosaur(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Dinosaur() {
        
    }

    @XmlElement (name = "Name")
    public String getName() {
        return name;
    }

    @XmlElement (name = "Description")
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Dinosaur{" + "Name=" + name + ", Description=" + description + '}';
    }
}
