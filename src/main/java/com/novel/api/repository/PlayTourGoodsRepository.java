package com.novel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.novel.api.entity.PlayTourGoods;

public interface PlayTourGoodsRepository extends JpaSpecificationExecutor<PlayTourGoods>,JpaRepository<PlayTourGoods, Integer> {
}
