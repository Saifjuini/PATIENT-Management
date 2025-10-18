package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface PatientsMapper {

    // Request DTO → Entity
    Patient toEntity(PatientRequestDTO dto);

    // Entity → Request DTO
    PatientRequestDTO toRequestDto(Patient entity);

    // Response DTO ↔ Entity
    Patient toEntity(PatientResponseDTO dto);
    PatientResponseDTO toResponseDto(Patient entity);

    // List mapping
    List<PatientResponseDTO> toResponseDtoList(List<Patient> patients);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatientFromDto(PatientRequestDTO dto, @MappingTarget Patient entity);
}
