package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.NovelBuy;

public interface NovelBuyRepository extends JpaSpecificationExecutor<NovelBuy>,JpaRepository<NovelBuy, Integer> {

	@Query(value = "select count(id) from tbl_novel_buy u  where  u.novel_id=?1 ", nativeQuery = true)
	public int findNovelBuyByNovelID(@Param("id")int id);
}
