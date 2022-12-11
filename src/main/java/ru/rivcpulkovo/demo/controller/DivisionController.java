package ru.rivcpulkovo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rivcpulkovo.demo.entity.Division;
import ru.rivcpulkovo.demo.service.DivisionService;

import java.util.Date;

@RestController
@RequestMapping("/division")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @GetMapping
    public Page<Division> getDivisionsByDate(Pageable pageable,
                                             @RequestParam(name = "dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                             @RequestParam(name = "dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return divisionService.getDivisionsByDate(pageable, dateFrom, dateTo);
    }

    @PostMapping
    public ResponseEntity<Division> addNewDivision(@RequestBody Division division) {
        Division newDivision = divisionService.addNewDivision(division);
        if (newDivision != null) {
            return ResponseEntity.ok(newDivision);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping
    public ResponseEntity<Division> updateDivision(@RequestBody Division division) {
        Division updatedDivision = divisionService.updateDivision(division);
        if (updatedDivision != null) {
            return ResponseEntity.ok(updatedDivision);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Division> deleteDivision(@RequestParam(name = "divisionId") Integer divisionId) {
        boolean deleteResult = divisionService.deleteDivision(divisionId);
        if (deleteResult) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
