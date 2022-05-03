package br.com.api.testeedson.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.testeedson.dtos.ExamDto;
import br.com.api.testeedson.model.CandidateModel;
import br.com.api.testeedson.model.ExamModel;
import br.com.api.testeedson.services.CandidateService;
import br.com.api.testeedson.services.ExamService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exam")
public class ExamController {	
	
	final ExamService examService;	

	public ExamController(ExamService examService) {		
		this.examService = examService;
	}
	
	@Autowired
	private CandidateService candidateService;
		
		
    @PostMapping
    public ResponseEntity<Object> saveExam(@RequestBody @Valid ExamDto examDto){
        Optional<CandidateModel> candidateModelOptional = candidateService.findById(examDto.getCandidateId());
        if (!candidateModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found.");
        }    	
    	
        var examModel = new ExamModel();        
        examModel.setCandidate(candidateModelOptional.get());
        BeanUtils.copyProperties(examDto, examModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.save(examModel));
    }
    
    @GetMapping
    public ResponseEntity<List<ExamModel>> getAllExams(){
    	return ResponseEntity.status(HttpStatus.OK).body(examService.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneExam(@PathVariable(value = "id") UUID id){
        Optional<ExamModel> examModelOptional = examService.findById(id);
        if (!examModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(examModelOptional.get());
    }   
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExam(@PathVariable(value = "id") UUID id){
        Optional<ExamModel> examModelOptional = examService.findById(id);
        if (!examModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found.");
        }
        examService.delete(examModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exam deleted successfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExam(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ExamDto examDto){
        Optional<ExamModel> parkingSpotModelOptional = examService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found.");
        }
        Optional<CandidateModel> candidateModelOptional = candidateService.findById(examDto.getCandidateId());
        if (!candidateModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate not found.");
        }        
        
        var examModel = new ExamModel();
        examModel.setCandidate(candidateModelOptional.get());
        BeanUtils.copyProperties(examDto, examModel);
        examModel.setId(parkingSpotModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(examService.save(examModel));
    } 	

}