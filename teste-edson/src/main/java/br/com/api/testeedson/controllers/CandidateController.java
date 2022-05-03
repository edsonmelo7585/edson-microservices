package br.com.api.testeedson.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.testeedson.services.CandidateService;
import br.com.api.testeedson.dtos.CandidateDto;
import br.com.api.testeedson.model.CandidateModel;


import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate")
public class CandidateController {
	
	final CandidateService candidateService;

	public CandidateController(CandidateService candidateService) {		
		this.candidateService = candidateService;
	}
	
    @PostMapping
    public ResponseEntity<Object> saveCandidate(@RequestBody @Valid CandidateDto candidateDto){
//        if(candidateService.findByCpf(candidateDto.getCpf())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
//        }

        var candidateModel = new CandidateModel();        
        BeanUtils.copyProperties(candidateDto, candidateModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.save(candidateModel));
    }
    
    @GetMapping
    public ResponseEntity<List<CandidateModel>> getAllCandidates(){
    	return ResponseEntity.status(HttpStatus.OK).body(candidateService.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCandidate(@PathVariable(value = "id") UUID id){
        Optional<CandidateModel> candidateModelOptional = candidateService.findById(id);
        if (!candidateModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidateModelOptional.get());
    }   
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable(value = "id") UUID id){
        Optional<CandidateModel> candidateModelOptional = candidateService.findById(id);
        if (!candidateModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found.");
        }
        candidateService.delete(candidateModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Candidate deleted successfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCandidate(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CandidateDto candidateDto){
        Optional<CandidateModel> parkingSpotModelOptional = candidateService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found.");
        }
        var candidateModel = new CandidateModel();
        BeanUtils.copyProperties(candidateDto, candidateModel);
        candidateModel.setId(parkingSpotModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.save(candidateModel));
    }    
}
