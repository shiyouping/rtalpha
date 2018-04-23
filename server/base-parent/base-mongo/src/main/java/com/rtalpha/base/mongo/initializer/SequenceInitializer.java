package com.rtalpha.base.mongo.initializer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.rtalpha.base.core.constant.SequenceName;
import com.rtalpha.base.mongo.document.Sequence;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@Component
public class SequenceInitializer implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(SequenceInitializer.class);
	private final MongoTemplate mongoTemplate;

	@Autowired
	public SequenceInitializer(@Nonnull MongoTemplate mongoTemplate) {
		checkNotNull(mongoTemplate, "mongoTemplate cannot be null");
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Initializing sequence numbers ...");

		for (String sequenceId : getSequenceNames()) {
			if (!isSequenceAvailable(sequenceId)) {
				createSequence(sequenceId);
				logger.info("Sequence {} initialized to 0", sequenceId);
			}
		}
	}

	private boolean isSequenceAvailable(String sequenceId) {
		Sequence sequence = mongoTemplate.findById(sequenceId, Sequence.class);
		return sequence != null;
	}

	private void createSequence(String sequenceId) {
		Sequence sequence = new Sequence();
		sequence.setId(sequenceId);
		sequence.setValue(0L);
		mongoTemplate.insert(sequence);
	}

	private List<String> getSequenceNames() {

		Field[] fields = SequenceName.class.getFields();
		List<String> names = Lists.newArrayListWithExpectedSize(fields.length);

		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				try {
					String name = (String) field.get(null);
					if (name != null) {
						names.add(name);
					}
				} catch (Exception e) {
					logger.error("Cannot get sequence name from {}", field.getName());
				}
			}
		}

		return names;
	}
}
