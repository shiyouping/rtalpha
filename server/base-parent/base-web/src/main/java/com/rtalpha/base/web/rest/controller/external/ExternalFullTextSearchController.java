package com.rtalpha.base.web.rest.controller.external;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.FullTextSearchService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.BaseModel;
import com.rtalpha.base.web.rest.response.external.ExternalBulkResponse;
import com.rtalpha.base.web.rest.response.external.ExternalPageResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for full text search operation, and delegates
 * {@linkplain FullTextSearchService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface ExternalFullTextSearchController<Dto extends BaseDto, Doc extends BaseDocument, M extends BaseModel>
		extends ExternalCrudController<Dto, Doc, M> {

	/**
	 * Delegate the full text search operation to its corresponding
	 * {@linkplain FullTextSearchService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ALL + "ByText")
	default ExternalBulkResponse<M> findAllByText(@Nullable Sort sort,
			@Nonnull @RequestParam(value = "text") String[] texts) {
		RequestChecker.checkNotEmptyCollection(Arrays.asList(texts), "text");

		return new ExternalBulkResponse<>(() -> {
			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			FullTextSearchService<Dto, Doc> service = (FullTextSearchService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			List<Dto> dtos = service.findAllByText(sort, texts);
			return mapper.toModelList(dtos);
		});
	}

	/**
	 * Delegate the full text search operation to its corresponding
	 * {@linkplain FullTextSearchService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_PAGE + "ByText")
	default ExternalPageResponse<M> findPageByText(@Nonnull Pageable pageable,
			@Nonnull @RequestParam(value = "text") String[] texts) {
		RequestChecker.checkNotNull(pageable, "pageable");
		RequestChecker.checkNotEmptyCollection(Arrays.asList(texts), "text");

		return new ExternalPageResponse<>(() -> {
			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			FullTextSearchService<Dto, Doc> service = (FullTextSearchService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			Page<Dto> page = service.findPageByText(pageable, texts);
			return mapper.toModelPage(page, pageable);
		});
	}
}
