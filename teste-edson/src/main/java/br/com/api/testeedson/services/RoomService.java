package br.com.api.testeedson.services;

import org.springframework.stereotype.Service;

import br.com.api.testeedson.repositories.RoomRepository;

@Service
public class RoomService {

	final RoomRepository RoomRepository;	
	
	public RoomService( RoomRepository RoomRepository) {
		this.RoomRepository = RoomRepository;
	}
}
