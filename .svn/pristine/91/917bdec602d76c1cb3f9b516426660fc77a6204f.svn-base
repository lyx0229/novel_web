package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.NovelRecord;

public interface NovelRecordRepository extends JpaSpecificationExecutor<NovelRecord>,JpaRepository<NovelRecord, Integer> {
	
	
	@Query(value = "select count(id) from tbl_novel_record u  where  u.novel_id=?1", nativeQuery = true)
	public int findNovelRecordByNovelId(@Param("novel_id")int novel_id);
	
	@Query(value = "select * from tbl_novel_record u  where  u.novel_id=?1 and user_id=?2 limit 1", nativeQuery = true)
	public NovelRecord findNovelRecordByUserIDNovelId(@Param("novel_id")int novel_id,@Param("user_id")int user_id);
}
