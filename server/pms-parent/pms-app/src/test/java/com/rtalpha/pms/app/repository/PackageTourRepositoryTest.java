package com.rtalpha.pms.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.google.common.collect.Lists;
import com.rtalpha.base.kernel.constant.NamedProfile;
import com.rtalpha.pms.app.document.PackageTour;

/**
 * @author Ricky
 * @since Apr 15, 2017
 *
 */
@DataMongoTest
@RunWith(SpringRunner.class)
@ActiveProfiles(NamedProfile.MODE_TEST)
@TestPropertySource("classpath:application-test.yml")
@TestExecutionListeners(PackageTourRepositoryTest.class)
public class PackageTourRepositoryTest extends AbstractTestExecutionListener {

	private static final String agent = "ricky.shih@qq.com";
	private static PackageTourRepository repository;

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		repository = testContext.getApplicationContext().getBean(PackageTourRepository.class);
		repository.save(Lists.newArrayList(firstPackageTour(), secondPackageTour()));
	}

	@Test
	public void shouldFindAllByAgent() {
		List<PackageTour> packageTours = repository.findAllByAgent(agent);
		assertThat(packageTours.size()).isEqualTo(2);

		packageTours = repository.findAllByAgent(null);
		assertThat(packageTours.size()).isEqualTo(0);
	}

	@Test
	public void shouldFindAllByAgentWithSort() {
		Sort sort = new Sort(Direction.ASC, "number");
		List<PackageTour> packageTours = repository.findAllByAgent(agent, sort);
		assertThat(packageTours.size()).isEqualTo(2);
		assertThat(packageTours.get(0).getNumber()).isEqualTo(firstPackageTour().getNumber());
		assertThat(packageTours.get(1).getNumber()).isEqualTo(secondPackageTour().getNumber());

		Sort nullSort = null;
		List<PackageTour> packageTourList = repository.findAllByAgent(agent, nullSort);
		assertThat(packageTourList.size()).isEqualTo(2);
	}

	@Test
	public void shouldFindPageByAgentWithPage() {
		Pageable pageable = new PageRequest(0, 1, new Sort(Direction.ASC, "number"));
		Page<PackageTour> page = repository.findPageByAgent(agent, pageable);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getTotalElements()).isEqualTo(2);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getContent()).isNotEmpty();
		assertThat(page.getContent().get(0).getNumber()).isEqualTo(firstPackageTour().getNumber());
	}

	@Test
	public void shouldFindPageByFullTextSearch() {
		Pageable pageable = new PageRequest(0, 1, new Sort(Direction.ASC, "number"));
		TextCriteria criteria = TextCriteria.forDefaultLanguage().caseSensitive(false).matching("SubjecT");
		Page<PackageTour> page = repository.findPageBy(criteria, pageable);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getTotalElements()).isEqualTo(1);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getContent()).isNotEmpty();
		assertThat(page.getContent().get(0).getNumber()).isEqualTo(firstPackageTour().getNumber());

		criteria = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny("sUbjEct", "GOod");
		page = repository.findPageBy(criteria, pageable);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getTotalElements()).isEqualTo(2);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getContent()).isNotEmpty();
	}

	private PackageTour firstPackageTour() {
		DateTime now = new DateTime();
		PackageTour packageTour = new PackageTour();
		packageTour.setNumber(1L);
		packageTour.setSubject("This is subject");
		packageTour.setSummary("This is summary");
		packageTour.setKeywords(Lists.newArrayList("Japan"));
		packageTour.setAgent(agent);
		packageTour.setCreatedTime(now);
		return packageTour;
	}

	private PackageTour secondPackageTour() {
		DateTime now = new DateTime();
		PackageTour packageTour = new PackageTour();
		packageTour.setNumber(2L);
		packageTour.setAgent(agent);
		packageTour.setCreatedTime(now);
		packageTour.setKeywords(Lists.newArrayList("good"));
		return packageTour;
	}
}
