package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.NovelStore;

public interface NovelStoreRepository extends JpaSpecificationExecutor<NovelStore>,JpaRepository<NovelStore, Integer> {
	
	
	@Query(value = "select count(id) from tbl_novel_store u  where  u.novel_id=?1", nativeQuery = true)
	public int findNovelStoreByNovelId(@Param("novel_id")int novel_id);
}
