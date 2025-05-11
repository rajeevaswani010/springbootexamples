package com.rjasw.demo.jpa_sql.srvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rjasw.demo.jpa_sql.api.msg.FileStorageSrvc;
import com.rjasw.demo.jpa_sql.entity.CaseFile;
import com.rjasw.demo.jpa_sql.repo.CaseFileRepo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CaseFileSrvc {

    @Autowired
    private CaseFileRepo caseFileRepository;
    
    @Autowired
    private FileStorageSrvc fileStorageSrvc;

    public List<CaseFile> getAllCases() {
        return caseFileRepository.findAll();
    }

    public Optional<CaseFile> getCaseById(Integer id) {
        return caseFileRepository.findById(id);
    }

    public CaseFile createCase(CaseFile caseFile, MultipartFile file) {    	
    	String url = fileStorageSrvc.store(file);
    	caseFile.setAttachment(url);
        return caseFileRepository.save(caseFile);
    }

    public CaseFile updateCase(Integer id, CaseFile updatedCase) {
        return caseFileRepository.findById(id)
            .map(existingCase -> {
                existingCase.setDescription(updatedCase.getDescription());
                existingCase.setStaff(updatedCase.getStaff());
                existingCase.setAgent(updatedCase.getAgent());
                existingCase.setCreatedAt(updatedCase.getCreatedAt());
                existingCase.setUpdatedAt(updatedCase.getUpdatedAt());
                existingCase.setAttachment(updatedCase.getAttachment());
                return caseFileRepository.save(existingCase);
            })
            .orElseThrow(() -> new RuntimeException("Case not found with id: " + id));
    }

    public void deleteCase(Integer id) {
        caseFileRepository.deleteById(id);
    }
}
