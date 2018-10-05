package com.spring.sales.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.sales.service.entity.SalesOrderId;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderId, Long> {

}
