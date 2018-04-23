package com.rtalpha.pms.app.rest.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.rest.controller.external.ExternalControllerMetaData;
import com.rtalpha.base.web.rest.controller.external.ExternalCreateController;
import com.rtalpha.base.web.rest.controller.external.ExternalDeleteController;
import com.rtalpha.base.web.rest.controller.external.ExternalFullTextSearchController;
import com.rtalpha.base.web.rest.controller.external.ExternalReadController;
import com.rtalpha.base.web.rest.controller.external.ExternalUpdateController;
import com.rtalpha.base.web.rest.response.external.ExternalPageResponse;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.rest.response.external.ExternalVoidResponse;
import com.rtalpha.pms.app.document.Product;
import com.rtalpha.pms.app.model.ProductFullModel;
import com.rtalpha.pms.app.model.ProductUpdateModel;
import com.rtalpha.pms.app.service.api.ProductService;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky Shi
 * @since Aug 18, 2017
 */
public abstract class ProductController<Dto extends ProductDto, Doc extends Product, F extends ProductFullModel, P extends ProductUpdateModel>
		implements ExternalCreateController<Dto, Doc, F>, ExternalReadController<Dto, Doc, F>,
		ExternalUpdateController<Dto, Doc, F, P>, ExternalDeleteController<Dto, Doc, F>,
		ExternalFullTextSearchController<Dto, Doc, F> {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private final ExternalControllerMetaData<Dto, Doc, F> metaData;

	public ProductController(@Nonnull CrudService<Dto, Doc> crudService, @Nonnull DtoModelMapper<Dto, F> mapper) {
		checkNotNull(crudService, "crudService cannot be null");
		checkNotNull(mapper, "mapper cannot be null");
		this.metaData = new ExternalControllerMetaData<>(crudService, mapper);
	}

	@DeleteMapping(path = RequestPath.METHOD_DELETE_ONE + "ByNumber")
	public ExternalVoidResponse deleteOneByNumber(@RequestParam("number") Long number) {
		logger.debug("Received a request for deleting product by number={}", number);

		return new ExternalVoidResponse(() -> {
			ProductService<Dto, Doc> service = (ProductService<Dto, Doc>) metaData.getCrudService();
			ProductDto product = service.findOneByNumber(number);
			if (product == null) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid number");
			}
			service.delete(product.getId());
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ByNumber")
	public ExternalSingleResponse<F> findOneByNumber(@RequestParam("number") Long number) {
		logger.debug("Received a request for finding one product by number={}", number);

		return new ExternalSingleResponse<>(() -> {
			ProductService<Dto, Doc> service = (ProductService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, F> mapper = metaData.getMapper();
			Dto product = service.findOneByNumber(number);
			return mapper.toModel(product);
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_PAGE + "ByAgent")
	public ExternalPageResponse<F> findPageByAgent(@RequestParam("agent") String agent, Pageable pageable) {
		logger.debug("Received a request for finding product page by agent={}, pagable={}", agent, pageable);

		return new ExternalPageResponse<>(() -> {
			ProductService<Dto, Doc> service = (ProductService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, F> mapper = metaData.getMapper();
			Page<Dto> page = service.findPageByAgent(agent, pageable);
			return mapper.toModelPage(page, pageable);
		});
	}

	@Override
	public ExternalControllerMetaData<Dto, Doc, F> getControllerMetaData() {
		return this.metaData;
	}
}