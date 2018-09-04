package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.novel.api.entity.NovelScheme;

public interface NovelSchemeRepository extends JpaSpecificationExecutor<NovelScheme>,JpaRepository<NovelScheme, Integer> {

}
