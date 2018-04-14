package com.ryanfranklin.audit.repository;

import com.ryanfranklin.audit.model.Audit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {

}
