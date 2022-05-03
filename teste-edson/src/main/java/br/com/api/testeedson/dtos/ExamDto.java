package br.com.api.testeedson.dtos;


import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ExamDto {
	
	@NotBlank
	@Size(max = 50)	
    private String subject;
	
	@NotNull
	private UUID candidateId;
		
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public UUID getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(UUID candidateId) {
		this.candidateId = candidateId;
	}
	
}
