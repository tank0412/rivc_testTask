package ru.rivcpulkovo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rivcpulkovo.demo.entity.Division;
import ru.rivcpulkovo.demo.repository.DivisionRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;

    public Page<Division> getDivisionsByDate(Pageable pageable, Date dateFrom, Date dateTo) {
        return divisionRepository.findByDateFromGreaterThanEqualAndDateToLessThanEqualOrDateToIsNull(pageable, dateFrom, dateTo);
    }

    public Division addNewDivision(Division division) {
        if (division.getDateFrom() != null) {
            return divisionRepository.save(division);
        } else {
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public Division updateDivision(Division updateDivision) {
        //expect that FE provide ID of old division
        Integer divisionId = updateDivision.getId();
        Date dateFrom = updateDivision.getDateFrom();

        if (dateFrom != null && dateFrom.compareTo(updateDivision.getDateTo()) <= 0 && divisionId != null) {
            Optional<Division> oldDivisionOptional = divisionRepository.findById(divisionId);
            if (oldDivisionOptional.isPresent()) {
                Division oldDivision = oldDivisionOptional.get();

                if (oldDivision.getParentDivision() != null && updateDivision.getParentDivision() == null) {
                    //I  guess I should not allow to remove parent division
                    return null;
                }

                //update old Division
                oldDivision.setDateTo(new Date());
                oldDivision.setCorrectionDate(new Date());
                divisionRepository.save(oldDivision);

                updateDivision.setId(null);
                updateDivision.setCreationDate(new Date());
                updateDivision.setCorrectionDate(null);
                return divisionRepository.save(updateDivision);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean deleteDivision(Integer divisionId) {
        if (divisionId != null) {
            List<Division> childDivisions = divisionRepository.findByParentDivisionId(divisionId);
            if (childDivisions.size() == 0) {
                int removeCount = divisionRepository.removeById(divisionId);
                return removeCount != 0;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
