package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.novel.api.entity.RechargeTemplate;

public interface RechargeTemplateRepository extends JpaSpecificationExecutor<RechargeTemplate>,JpaRepository<RechargeTemplate, Integer> {
}
