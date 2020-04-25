package com.leyou.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.leyou.item.api.SpecificationApi;

@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi{
}
