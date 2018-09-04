package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.ChapterBuyInfo;

public interface ChapterBuyRepository extends JpaSpecificationExecutor<ChapterBuyInfo>,JpaRepository<ChapterBuyInfo, Integer> {

	@Query(value = "select count(id) from tbl_chapter_buy u  where  u.novel_detail_id=?1 and u.user_id=?2 ", nativeQuery = true)
	public int findChapterBuyByNovelID(@Param("novel_detail_id")int novel_detail_id,@Param("user_id")int user_id);
}
