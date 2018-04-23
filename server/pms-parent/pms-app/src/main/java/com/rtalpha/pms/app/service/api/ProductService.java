package com.rtalpha.pms.app.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.DeleteService;
import com.rtalpha.base.mongo.crud.FullTextSearchService;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.base.mongo.crud.UpdateService;
import com.rtalpha.pms.app.document.Product;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky
 * @since May 17, 2017
 */
public interface ProductService<Dto extends ProductDto, Doc extends Product> extends CreateService<Dto, Doc>,
		ReadService<Dto, Doc>, UpdateService<Dto, Doc>, DeleteService<Dto, Doc>, FullTextSearchService<Dto, Doc> {

	@Nullable
	List<Dto> findAllByAgent(@Nonnull String agent);

	@Nullable
	List<Dto> findAllByAgent(@Nonnull String agent, @Nullable Sort sort);

	@Nullable
	Dto findOneByNumber(@Nonnull Long number);

	@Nonnull
	Page<Dto> findPageByAgent(@Nonnull String agent, @Nonnull Pageable pageable);
}