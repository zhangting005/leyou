package com.leyou.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.leyou.LySearchApplication;
import com.leyou.client.CategoryClient;
import com.leyou.client.GoodsClient;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.pojo.Goods;
import com.leyou.repository.GoodsRepository;
import com.leyou.service.SearchService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchApplication.class)
public class loadDataTest {

	@Autowired
	private GoodsRepository goodsRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private GoodsClient goodsClient;

	@Autowired
	private SearchService searchService;

	@Test
	public void loadData() {
		// 创建索引
		this.elasticsearchTemplate.createIndex(Goods.class);
		// 配置映射
		this.elasticsearchTemplate.putMapping(Goods.class);
		int page = 1;
		int rows = 100;
		int size = 0;
		do {
			// 查询分页数据
			PageResult<SpuBo> result = this.goodsClient.querySpuByPage(page, rows, true, null);
			List<SpuBo> spus = result.getItems();
			size = spus.size();
			// 创建Goods集合
			List<Goods> goodsList = new ArrayList<>();
			// 遍历spu
			for (SpuBo spu : spus) {
				try {
					Goods goods = this.searchService.buildGoods(spu);
					goodsList.add(goods);
				} catch (Exception e) {
					break;
				}
			}

			if (goodsList != null && goodsList.size() > 0) {
				this.goodsRepository.saveAll(goodsList);
			}
			page++;
		} 
		while (size == 100);
	}
}
