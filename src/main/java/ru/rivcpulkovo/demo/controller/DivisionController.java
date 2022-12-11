package ru.rivcpulkovo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rivcpulkovo.demo.entity.Division;
import ru.rivcpulkovo.demo.service.DivisionService;

import java.util.Date;

@Controller
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @GetMapping(name = "division")
    public Page<Division> getDivisionsByDate(Pageable pageable, @RequestParam(name = "dateTo") Date dateTo,
                                             @RequestParam(name = "dateFrom") Date dateFrom) {
        return divisionService.getDivisionsByDate(pageable, dateTo, dateFrom);
    }

    @PostMapping(name = "division")
    public ResponseEntity<Division> addNewDivision(@RequestParam(name = "division") Division division) {
        Division newDivision = divisionService.addNewDivision(division);
        if (newDivision != null) {
            return ResponseEntity.ok(newDivision);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping(name = "division")
    public ResponseEntity<Division> updateDivision(@RequestParam(name = "division") Division division) {
        Division updatedDivision = divisionService.updateDivision(division);
        if (updatedDivision != null) {
            return ResponseEntity.ok(updatedDivision);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(name = "division")
    public ResponseEntity<Division> deleteDivision(@RequestParam(name = "division") Division division) {
        Division deletedDivision = divisionService.deleteDivision(division);
        if (deletedDivision != null) {
            return ResponseEntity.ok(deletedDivision);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
