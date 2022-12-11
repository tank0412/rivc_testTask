package ru.rivcpulkovo.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rivcpulkovo.demo.entity.Division;

import java.util.Date;
import java.util.List;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Integer> {
    Page<Division> findByDateFromGreaterThanEqualAndDateToLessThanEqualOrDateToIsNull(Pageable pageable, Date dateFrom, Date dateTo);
    List<Division> findByParentDivisionId(Integer parentDivisionId);
    int removeById(Integer id);

}
