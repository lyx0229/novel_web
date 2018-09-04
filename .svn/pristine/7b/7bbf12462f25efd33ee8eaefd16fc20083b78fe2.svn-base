package com.novel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.NovelDetail;

public interface NovelDetailRepository extends JpaSpecificationExecutor<NovelDetail>,JpaRepository<NovelDetail, Integer> {

	@Query(value="select u.* from tbl_novel_detail u where u.novel_id=?1 order by id asc", nativeQuery = true)
	List<NovelDetail> findByNovelDetailByNovelID(@Param("novel_id")int novel_id);
	
	/**
	 * 查询下一个章节id
	 * @param novel_id
	 * @param novel_detail_id
	 * @return
	 */
	@Query(value="select u.* from tbl_novel_detail u where u.novel_id=?1 and  u.id>?2 order by id asc limit 1", nativeQuery = true)
	NovelDetail findNextID(@Param("novel_id")int novel_id,@Param("novel_detail_id")int novel_detail_id);
	/**
	 * 查询上一个章节id
	 * @param novel_id
	 * @param novel_detail_id
	 * @return
	 */
	@Query(value="select u.* from tbl_novel_detail u where u.novel_id=?1 and  u.id<?2 order by id desc limit 1", nativeQuery = true)
	NovelDetail findLastID(@Param("novel_id")int novel_id,@Param("novel_detail_id")int novel_detail_id);
}
