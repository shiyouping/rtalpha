package com.rtalpha.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.rtalpha.base.core.constant.SequenceName;
import com.rtalpha.base.kernel.constant.NamedProfile;
import com.rtalpha.base.mongo.document.Sequence;
import com.rtalpha.base.mongo.service.api.SequenceService;
import com.rtalpha.base.mongo.service.impl.SequenceServiceImpl;

@DataMongoTest
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles(NamedProfile.MODE_TEST)
@TestPropertySource("classpath:application-test.yml")
@ContextConfiguration(classes = { SequenceServiceImpl.class, MongoRepositoriesAutoConfiguration.class })
public class SequenceServiceTest {

	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private MongoTemplate mongoTemplate;
	private AtomicInteger count = new AtomicInteger(0);

	@Before
	public void init() {
		Sequence sequence = new Sequence();
		sequence.setId(SequenceName.PRODUCT);
		sequence.setValue(0L);
		mongoTemplate.insert(sequence);
	}

	@After
	public void cleanup() {
		mongoTemplate.dropCollection(Sequence.class);
	}

	@Test
	public void shouldGetNextSequence() throws InterruptedException {
		for (int i = 0; i < 1200; i++) {
			new Thread(new SequenceServiceRunnable(count, sequenceService)).start();

			if (i % 400 == 0) {
				Thread.sleep(1000 * 5);
			}
		}

		Thread.sleep(1000 * 15);
		assertThat(sequenceService.getNextSequence(SequenceName.PRODUCT)).isEqualTo(count.incrementAndGet());
	}

	private class SequenceServiceRunnable implements Runnable {

		private final AtomicInteger count;
		private final SequenceService service;

		public SequenceServiceRunnable(AtomicInteger count, SequenceService service) {
			this.count = count;
			this.service = service;
		}

		@Override
		public void run() {
			service.getNextSequence(SequenceName.PRODUCT);
			this.count.incrementAndGet();
		}
	}
}
