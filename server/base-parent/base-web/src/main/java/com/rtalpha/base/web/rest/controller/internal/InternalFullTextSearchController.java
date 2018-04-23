package com.rtalpha.base.web.rest.controller.internal;

import java.util.Arrays;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.FullTextSearchService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.rest.response.internal.InternalBulkResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for full text search operation, and delegates
 * {@linkplain FullTextSearchService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface InternalFullTextSearchController<Dto extends BaseDto, Doc extends BaseDocument>
		extends InternalCrudController<Dto, Doc> {

	/**
	 * Delegate the full text search operation to its corresponding
	 * {@linkplain FullTextSearchService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ALL + "ByText")
	default InternalBulkResponse<Dto> findAllByText(@Nonnull @RequestParam(value = "text") String[] texts) {
		RequestChecker.checkNotEmptyCollection(Arrays.asList(texts), "text");
		return new InternalBulkResponse<>(() -> {
			FullTextSearchService<Dto, Doc> service = (FullTextSearchService<Dto, Doc>) getCrudService();
			return service.findAllByText(null, texts);
		});
	}
}
