package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientsMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById( @PathVariable String id){
        return ResponseEntity.ok().body(patientService.findPatientById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO updatedPatient= patientService.updatePatient(patientRequestDTO,id);
        return ResponseEntity.ok().body(updatedPatient);
    }

}
