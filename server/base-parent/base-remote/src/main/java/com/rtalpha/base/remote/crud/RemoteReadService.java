package com.rtalpha.base.remote.crud;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Defines the minimum methods for remote DTO read. Extra methods should be
 * defined in the sub-interfaces if necessary
 * 
 * @author Ricky Shi
 * @since Jul 12, 2017
 * @param <D>
 */
public interface RemoteReadService<D extends BaseDto> extends RemoteCrudService<D> {
	/**
	 * Find the unique dto for the given id
	 *
	 * @param id
	 *            the unique id
	 * @return the found dto. Null if not found.
	 */
	@Nullable
	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ONE + "ById")
	D findOneById(@Nonnull @RequestParam(name = "id") String id);

	/**
	 * Find all dtos ˙
	 * 
	 * @return the found dtos. Null if not found.
	 */
	@Nullable
	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ALL)
	List<D> findAll();
}
