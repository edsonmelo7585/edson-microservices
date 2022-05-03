package br.com.api.testeedson.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="exam")
//@SequenceGenerator(name="exam_seq", sequenceName = "exam_seq", initialValue = 1, allocationSize = 1)
public class ExamModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_seq")
//    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
    @Column(nullable = false, length = 50)
    private String subject;
    
    @OneToOne()
    private CandidateModel candidate;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

	public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	} 
    
}
