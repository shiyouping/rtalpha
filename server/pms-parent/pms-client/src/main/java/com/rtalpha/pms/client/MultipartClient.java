package com.rtalpha.pms.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.remote.crud.RemoteCreateService;
import com.rtalpha.base.remote.crud.RemoteUpdateService;
import com.rtalpha.pms.client.fallback.MultipartClientFallbackFactory;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author ricky.shi
 * @since 28 Mar 2018
 */
@FeignClient(name = "${service-name.pms}", fallbackFactory = MultipartClientFallbackFactory.class)
public interface MultipartClient extends RemoteCreateService<MultipartDto>, RemoteUpdateService<MultipartDto> {

	@RequestMapping(method = RequestMethod.POST, path = RequestPath.METHOD_CREATE_ONE + "WithFileType")
	MultipartDto createOne(@RequestBody byte[] data, @PathVariable("fileType") String fileType);

	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ONE)
	MultipartDto findOne(@RequestParam("md5") String md5, @RequestParam("crc32") String crc32,
			@RequestParam("sha256") String sha256);
}