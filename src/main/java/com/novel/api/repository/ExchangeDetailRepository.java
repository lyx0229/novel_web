package com.novel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.ExchangeDetail;

public interface ExchangeDetailRepository extends JpaSpecificationExecutor<ExchangeDetail>,JpaRepository<ExchangeDetail, Integer> {
	@Query(value = "select t from ExchangeDetail t where t.user_id = :user_id")
	List<ExchangeDetail> findExchangeDetailByUserid(@Param("user_id")int user_id);
}
