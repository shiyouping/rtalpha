package com.rtalpha.pms.client;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.remote.crud.RemoteCreateService;
import com.rtalpha.base.remote.crud.RemoteDeleteService;
import com.rtalpha.base.remote.crud.RemoteFullTextSearchService;
import com.rtalpha.base.remote.crud.RemoteReadService;
import com.rtalpha.base.remote.crud.RemoteUpdateService;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author ricky.shi
 * @param <D>
 * @since 29 Mar 2018
 */
public interface ProductClient<D extends ProductDto> extends RemoteCreateService<D>, RemoteReadService<D>,
		RemoteUpdateService<D>, RemoteDeleteService<D>, RemoteFullTextSearchService<D> {

	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ALL + "ByAgent")
	public List<D> findAllByAgent(@Nonnull @RequestParam String agent);

	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ONE + "ByNumber")
	public D findOneByNumber(@Nonnull @RequestParam Long number);
}
