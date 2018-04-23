package com.rtalpha.base.remote.crud;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Defines the minimum methods for remote DTO full-text search. Extra methods
 * should be defined in the sub-interfaces if necessary
 * 
 * @author Ricky Shi
 * @since Jul 12, 2017
 * @param <D>
 */
public interface RemoteFullTextSearchService<D extends BaseDto> extends RemoteCrudService<D> {

	@Nonnull
	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ALL + "ByText")
	List<D> findAllByText(@Nonnull @RequestParam(name = "text") String[] texts);
}
