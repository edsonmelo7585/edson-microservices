package br.com.api.testeedson.services;

import org.springframework.stereotype.Service;

import br.com.api.testeedson.repositories.AvailabilityRepository;

@Service
public class AvailabilityService {

	final AvailabilityRepository AvailabilityRepository;	
	
	public AvailabilityService( AvailabilityRepository AvailabilityRepository) {
		this.AvailabilityRepository = AvailabilityRepository;
	}
}
