package com.novel.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.novel.api.entity.Novel;
import com.novel.api.entity.NovelBuy;
import com.novel.api.entity.NovelDetail;
import com.novel.api.entity.NovelRecord;
import com.novel.api.entity.NovelScheme;
import com.novel.api.entity.NovelStore;
import com.novel.api.pojo.NovelDetailView;
import com.novel.api.pojo.NovelRecordView;
import com.novel.api.pojo.NovelView;
import com.novel.api.repository.NovelBuyRepository;
import com.novel.api.repository.NovelDetailRepository;
import com.novel.api.repository.NovelRecordRepository;
import com.novel.api.repository.NovelRepository;
import com.novel.api.repository.NovelSchemeRepository;
import com.novel.api.repository.NovelStoreRepository;

@Service
public class NovelService {
	@Autowired
	private NovelRepository novelRep;
	@Autowired
	private NovelBuyRepository novelBuy;

	@Autowired
	private NovelStoreRepository novelStoreRepository;
	@Autowired
	private NovelDetailRepository novelDetailRep;
	@Autowired
	private NovelRecordRepository novelRecordRep;
	@Autowired
	private NovelSchemeRepository novelSchemeRep;

	@Autowired
	private EntityManager entityManager;

	public Novel saveAndUpdate(Novel po) {
		return novelRep.saveAndFlush(po);
	}

	public NovelDetail saveAndUpdate(NovelDetail po) {
		return novelDetailRep.saveAndFlush(po);
	}
	public NovelStore saveAndUpdate(NovelStore po) {
		return novelStoreRepository.saveAndFlush(po);
	}
	public NovelStore findNovelStoreByID(int id) {
		return novelStoreRepository.findOne(id);
	}
	public void delteteNovelStoreByID(int id) {
		 novelStoreRepository.delete(id);
	}
	public NovelRecord saveAndUpdate(NovelRecord po) {
		return novelRecordRep.saveAndFlush(po);
	}
	public NovelRecord findNovelRecordByID(int id) {
		return novelRecordRep.findOne(id);
	}
	public void delteteNovelRecordByID(int id) {
		novelRecordRep.delete(id);
	}
	public NovelBuy saveAndUpdate(NovelBuy po) {
		return novelBuy.saveAndFlush(po);
	}

	public NovelBuy findNovelBuyByID(int id) {
		return novelBuy.findOne(id);
	}

	public int findNovelBuyByNovelID(Integer id) {
		return novelBuy.findNovelBuyByNovelID(id);
	}

	public Novel findNovelByID(int id) {
		return novelRep.findOne(id);
	}

	
	public NovelDetail findNovelDetailByID(int id) {
		return novelDetailRep.findOne(id);
	}

	public NovelScheme saveAndUpdate(NovelScheme po) {
		return novelSchemeRep.saveAndFlush(po);
	}

	public NovelScheme findNovelSchemeByID(int id) {
		return novelSchemeRep.findOne(id);
	}

	public NovelView findNovelViewByID(int id) {
		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery("select u.* from  tbl_novel u  where u.id=:id ");
		nativeQuery.setParameter("id", id);
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public List<Novel> findSearch(Novel model) {
		List<Novel> resultList = null;
		Specification<Novel> querySpecifi = new Specification<Novel>() {
			@Override
			public Predicate toPredicate(Root<Novel> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (model.getSex_status() != 2) {
					predicates.add(
							criteriaBuilder.equal(root.get("sex_status").as(Integer.class), model.getSex_status()));
				}
				if (model.getType_status() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("type_status").as(Integer.class), model.getType_status()));
				}
				if (model.getNovel_state() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("novel_state").as(Integer.class), model.getNovel_state()));
				}
				if (model.getHot_status() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("hot_status").as(Integer.class), model.getHot_status()));
				}

				if (model.getNovel_name() != null && !"".equals(model.getNovel_name())) {
					predicates.add(criteriaBuilder.like(root.get("novel_name").as(String.class),
							"%" + model.getNovel_name() + "%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		resultList = novelRep.findAll(querySpecifi);
		return resultList;
	}

	/**
	 * 分页查询
	 */
	public Page<Novel> findNovelByCondition(Novel model, Pageable basicPage) {

		Specification<Novel> specification = new Specification<Novel>() {
			@Override
			public Predicate toPredicate(Root<Novel> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (model.getSex_status() != 2) {
					predicates.add(
							criteriaBuilder.equal(root.get("sex_status").as(Integer.class), model.getSex_status()));
				}
				if (model.getType_status() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("type_status").as(Integer.class), model.getType_status()));
				}
				if (model.getNovel_state() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("novel_state").as(Integer.class), model.getNovel_state()));
				}
				if (model.getHot_status() != 0) {
					predicates.add(
							criteriaBuilder.equal(root.get("hot_status").as(Integer.class), model.getHot_status()));
				}
				if (model.getNovel_name() != null && !"".equals(model.getNovel_name())) {
					predicates.add(criteriaBuilder.like(root.get("novel_name").as(String.class),
							"%" + model.getNovel_name() + "%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return novelRep.findAll(specification, basicPage);
	}
	/**
	 * 分页查询
	 */
	public Page<Novel> findNovelPHByCondition(Novel model, Pageable basicPage) {
		
		Specification<Novel> specification = new Specification<Novel>() {
			@Override
			public Predicate toPredicate(Root<Novel> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				
				List<Predicate> predicates = new ArrayList<>();
				if (model.getSex_status() != 2) {
					predicates.add(
							criteriaBuilder.equal(root.get("sex_status").as(Integer.class), model.getSex_status()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return novelRep.findAll(specification, basicPage);
	}

	public List<NovelView> findByNovelViewByUserid(int user_id) {
		// 执行原生SQL
		String sql = "select u.*,o.id as store_id,o.update_time from tbl_novel_store o  left join  tbl_novel u on  o.novel_id=u.id where o.user_id=:user_id";
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("user_id", user_id);
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 目录
	 * 
	 * @param novel_id
	 * @return
	 */
	public List<NovelDetail> findByNovelDetailByNovelID(int novel_id) {
		return novelDetailRep.findByNovelDetailByNovelID(novel_id);

	}

	public List<NovelView> findNovelViewByCondition(Novel model) {

		String sql = "select u.* from  tbl_novel u where u.sex_status=:sex_status and u.hot_status=:hot_status";

		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("sex_status", model.getSex_status());
		nativeQuery.setParameter("hot_status", model.getHot_status());
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 排行榜
	 * 
	 * @param model
	 * @return
	 */
	public List<Novel> findNovelView(Novel model) {

		String sql = "select u.* from  tbl_novel u where u.sex_status=:sex_status order by novel_number_votes desc";

		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("sex_status", model.getSex_status());
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(Novel.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<Novel> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	public List<NovelView> findNovelViewByConditionLimit(Novel model) {

		String sql = "select u.* from  tbl_novel u where u.sex_status=:sex_status and u.hot_status=:hot_status limit 5";

		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("sex_status", model.getSex_status());
		nativeQuery.setParameter("hot_status", model.getHot_status());
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 排行榜
	 * 
	 * @param model
	 * @return
	 */
	public List<NovelView> findNovelViewLimit(Novel model) {

		String sql = "select u.* from  tbl_novel u where u.sex_status=:sex_status order by novel_number_votes desc limit 5";

		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("sex_status", model.getSex_status());
		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	public List<NovelDetailView> findNovelDetailViewByCondition(JSONObject param) {

		String sql = "select a.id,a.novel_id,a.chapter_name,a.chapter_url,a.update_time, "
				+ "(case when a.free_state=1 and b.id is not NULL then 2  else a.free_state end) as free_state"
				+ " from  tbl_novel_detail a left join (select * from  tbl_chapter_buy where user_id=:user_id) b on a.id=b.novel_detail_id where a.novel_id=:novel_id";
		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("user_id", param.getInteger("user_id"));
		nativeQuery.setParameter("novel_id", param.getInteger("novel_id"));

		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelDetailView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelDetailView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 添加书库统计
	 * 
	 * @param id
	 * @return
	 */
	public int findNovelStoreByNovelId(int id) {
		return novelStoreRepository.findNovelStoreByNovelId(id);
	}

	/**
	 * 阅读记录统计
	 * 
	 * @param id
	 * @return
	 */
	public int findNovelRecordByNovelId(int id) {
		return novelRecordRep.findNovelRecordByNovelId(id);
	}

	/**
	 * 阅读记录查询
	 * 
	 * @param inovel__id
	 * @param user_id
	 * @return
	 */
	public NovelRecord findNovelRecordByUserIDNovelId(int novel_id, int user_id) {
		return novelRecordRep.findNovelRecordByUserIDNovelId(novel_id, user_id);
	}

	/**
	 * 个人阅读记录查询
	 * 
	 * @param user_id
	 * @return
	 */
	public List<NovelRecordView> findNovelRecordViewByUserId(int user_id) {

		String sql = "select a.*,b.chapter_name,b.chapter_url,c.novel_author,c.novel_name "
				+ " from tbl_novel_record a left join tbl_novel_detail b on a.novel_detail_id=b.id "
				+ "left join tbl_novel c on  b.novel_id=c.id where a.user_id=:user_id";
		// 执行原生SQL
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("user_id", user_id);

		// 指定返回对象类型
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NovelRecordView.class));
		// 返回对象 List<T>
		@SuppressWarnings("unchecked")
		List<NovelRecordView> list = nativeQuery.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 分页查询
	 */
	public Page<NovelDetail> findNovelDetailByCondition(NovelDetail model, Pageable basicPage) {

		Specification<NovelDetail> specification = new Specification<NovelDetail>() {
			@Override
			public Predicate toPredicate(Root<NovelDetail> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (model.getNovel_id() != 0) {
					predicates.add(criteriaBuilder.equal(root.get("novel_id").as(Integer.class), model.getNovel_id()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return novelDetailRep.findAll(specification, basicPage);
	}
	
	public int findNextID(int novel_id,int novel_detail_id){
		NovelDetail detail=novelDetailRep.findNextID(novel_id, novel_detail_id);
		int id=0;
		if(detail!=null ){
			id=detail.getId();
		}
		return id;
	}
	public int findLastID(int novel_id,int novel_detail_id){
		NovelDetail detail=novelDetailRep.findLastID(novel_id, novel_detail_id);
		int id=0;
		if(detail!=null ){
			id=detail.getId();
		}
		return id;
	}
}
