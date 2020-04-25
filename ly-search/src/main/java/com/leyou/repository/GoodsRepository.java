package com.leyou.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.leyou.pojo.Goods;

public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
	
}
