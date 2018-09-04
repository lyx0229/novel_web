package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.PlayTourDetail;

public interface PlayTourDetailRepository extends JpaSpecificationExecutor<PlayTourDetail>,JpaRepository<PlayTourDetail, Integer> {
	/**
	 * 打赏统计
	 * @param novel_id
	 * @return
	 */
	@Query(value = "select count(id) from tbl_play_tour_detail u  where  u.novel_id=?1", nativeQuery = true)
	public int findGoodsByNovelId(@Param("novel_id")int novel_id);
}
