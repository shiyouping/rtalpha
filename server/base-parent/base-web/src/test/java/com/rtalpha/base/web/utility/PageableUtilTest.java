package com.rtalpha.base.web.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.MultiValueMap;

public class PageableUtilTest {

	@Test
	public void shouldToRequestParameters() {
		Pageable pageable = aPageable();
		String parameters = PageableUtil.toParameterString(pageable);
		assertThat(parameters).isEqualToIgnoringCase("&page=0&size=3&sort=name,asc&sort=age,desc");
	}

	@Test
	public void shouldToRequestMap() {
		Pageable pageable = aPageable();
		MultiValueMap<String, String> map = PageableUtil.toParameterMap(pageable);
		assertThat(map.size()).isEqualTo(3);
		assertThat(map.get("page").get(0)).isEqualTo("0");
		assertThat(map.get("size").get(0)).isEqualTo("3");
		assertThat(map.get("sort").size()).isEqualTo(2);
		assertThat(map.get("sort").get(0)).isEqualToIgnoringCase("name,asc");
		assertThat(map.get("sort").get(1)).isEqualToIgnoringCase("age,desc");
	}

	private Pageable aPageable() {
		Order order1 = new Order(Direction.ASC, "name");
		Order order2 = new Order(Direction.DESC, "age");
		Sort sort = new Sort(order1, order2);
		Pageable pageable = new PageRequest(0, 3, sort);
		return pageable;
	}
}
