package com.rtalpha.pms.app.rest.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.web.rest.controller.internal.InternalCreateController;
import com.rtalpha.base.web.rest.controller.internal.InternalDeleteController;
import com.rtalpha.base.web.rest.controller.internal.InternalFullTextSearchController;
import com.rtalpha.base.web.rest.controller.internal.InternalReadController;
import com.rtalpha.base.web.rest.controller.internal.InternalUpdateController;
import com.rtalpha.base.web.rest.response.internal.InternalBulkResponse;
import com.rtalpha.base.web.rest.response.internal.InternalSingleResponse;
import com.rtalpha.pms.app.document.Product;
import com.rtalpha.pms.app.service.api.ProductService;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky Shi
 * @since Jul 31, 2017
 */
public class ProductController<Dto extends ProductDto, Doc extends Product> implements
		InternalCreateController<Dto, Doc>, InternalReadController<Dto, Doc>, InternalUpdateController<Dto, Doc>,
		InternalDeleteController<Dto, Doc>, InternalFullTextSearchController<Dto, Doc> {

	private final ProductService<Dto, Doc> service;

	public ProductController(@Nonnull ProductService<Dto, Doc> service) {
		checkNotNull(service, "service cannot be null");
		this.service = service;
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ALL + "ByAgent")
	public InternalBulkResponse<Dto> findAllByAgent(@RequestParam String agent) {
		return new InternalBulkResponse<>(() -> {
			return service.findAllByAgent(agent);
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ByNumber")
	public InternalSingleResponse<Dto> findOneByNumber(@RequestParam Long number) {
		return new InternalSingleResponse<>(() -> {
			return service.findOneByNumber(number);
		});
	}

	@Override
	public CrudService<Dto, Doc> getCrudService() {
		return service;
	}
}
