package br.com.api.testeedson.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="room")
//@SequenceGenerator(name="room_seq", sequenceName = "room_seq", initialValue = 1, allocationSize = 1)
public class RoomModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
//    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;    
	
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
//    @JoinTable(name="availability",
//			   joinColumns={@JoinColumn(name="room_id", referencedColumnName="id")})
    private List<AvailabilityModel> availabilities;   

	public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AvailabilityModel> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<AvailabilityModel> availabilities) {
		this.availabilities = availabilities;
	}    
}
