package br.com.api.testeedson.dtos;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class AvailabilityDto {
	
	@NotBlank		
	private Date availabilityDate;

	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
	
}
