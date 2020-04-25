package com.leyou.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.leyou.item.api.CategoryApi;

@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
