package com.novel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.CheckInfo;

public interface CheckInfoRepository extends JpaSpecificationExecutor<CheckInfo>,JpaRepository<CheckInfo, Integer> {
	
	List<CheckInfo> findCheckInfoByUserID(int user_id);
	@Query(value = "select t.* from tbl_check_info t where t.user_id = :userID and  DATE_FORMAT(t.check_time,'%Y-%m-%d') = :check_time limit 1",nativeQuery=true)
	CheckInfo findByUserIDAndTime(@Param("userID")int userID ,@Param("check_time")String check_time);
}
