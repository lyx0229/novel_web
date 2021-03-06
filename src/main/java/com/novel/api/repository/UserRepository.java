package com.novel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novel.api.entity.WeixinUserInfo;

public interface UserRepository extends JpaSpecificationExecutor<WeixinUserInfo>,JpaRepository<WeixinUserInfo, Integer> {
	
	//利用原生的SQL进行查询操作
	@Query(value = "select u.* from tbl_user u  where  u.open_id=?1", nativeQuery = true)
	public WeixinUserInfo findUserInfoByOpenID(String openID);
	
	
	@Query(value = "select count(id) from tbl_user u  where  u.id=?1 and u.vip_end_time>= NOW()", nativeQuery = true)
	public int findUserByIdAndVip(@Param("id")int id);
	
	@Query(value = "select u.* from tbl_user u  where  u.vip_status=1 and u.vip_end_time < NOW()", nativeQuery = true)
	public List<WeixinUserInfo> findUserInfoByVip();
}
