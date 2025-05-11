package com.rjasw.demo.jpa_sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rjasw.demo.jpa_sql.api.msg.CaseFileParams;
import com.rjasw.demo.jpa_sql.entity.CaseFile;
import com.rjasw.demo.jpa_sql.srvc.CaseFileSrvc;

import java.util.List;

@RestController
@RequestMapping("/demo/cases")
public class CaseFileController {

    @Autowired
    private CaseFileSrvc caseFileService;

    @GetMapping
    public List<CaseFile> getAllCases() {
        return caseFileService.getAllCases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseFile> getCaseById(@PathVariable Integer id) {
        return caseFileService.getCaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CaseFile> createCaseWithFile(
            @RequestPart("caseFile") CaseFile caseFile,
            @RequestPart("file") MultipartFile file) {
        CaseFile savedCase = caseFileService.createCase(caseFile, file);
        return ResponseEntity.ok(savedCase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseFile> updateCase(@PathVariable Integer id, @RequestBody CaseFile updatedCase) {
        try {
            return ResponseEntity.ok(caseFileService.updateCase(id, updatedCase));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Integer id) {
        caseFileService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }
}
