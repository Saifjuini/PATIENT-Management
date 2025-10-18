package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientsMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientsMapper patientsMapper;

    public PatientService(PatientRepository patientRepository, PatientsMapper patientsMapper) {
        this.patientRepository = patientRepository;
        this.patientsMapper = patientsMapper;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientsMapper.toResponseDtoList(patients);
    }

    public Optional<Patient> findPatientById(String id) {
        return patientRepository.findById(UUID.fromString(id));
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail()
            );
        }

        Patient newPatient = patientsMapper.toEntity(patientRequestDTO);
        patientRepository.save(newPatient);

        return patientsMapper.toResponseDto(newPatient);
    }

    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO, UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail()
            );
        }
        patientsMapper.updatePatientFromDto(patientRequestDTO, patient);
        patientRepository.save(patient);

        return patientsMapper.toResponseDto(patient);
    }
}
