package com.rtalpha.base.remote.crud;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Super interface of all remote CRUD services annotated by
 * {@linkplain FeignClient}
 * 
 * @author ricky.shi
 * @since 14 Mar 2018
 */
public interface RemoteCrudService<D extends BaseDto> {
}
