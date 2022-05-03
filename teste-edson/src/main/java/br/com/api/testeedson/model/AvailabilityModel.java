package br.com.api.testeedson.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="availability")
//@SequenceGenerator(name="availability_seq", sequenceName = "availability_seq", initialValue = 1, allocationSize = 1)
public class AvailabilityModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "availability_seq")
//    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;	
        
//    @Column(name = "room_id")
//    private UUID roomId; 
    
    @ManyToOne
    private RoomModel room;        
    
    @OneToOne
    private ExamModel exam;   

	@Column(nullable = false)
    private Date availabilityDate;	
	
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RoomModel getRoom() {
		return room;
	}

	public void setRoom(RoomModel room) {
		this.room = room;
	}

	public ExamModel getExam() {
		return exam;
	}

	public void setExam(ExamModel exam) {
		this.exam = exam;
	}

	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}    
   
}
