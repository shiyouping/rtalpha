package com.rtalpha.base.mongo.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.rtalpha.base.mongo.document.Sequence;
import com.rtalpha.base.mongo.service.api.SequenceService;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@Service
public class SequenceServiceImpl implements SequenceService {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public SequenceServiceImpl(@Nonnull MongoTemplate mongoTemplate) {
		checkNotNull(mongoTemplate, "mongoTemplate cannot be null");
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	@Nonnegative
	public long getNextSequence(@Nonnull String sequenceName) {
		checkNotNull(sequenceName, "sequenceName cannot be null");

		Query query = Query.query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("value", 1);
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
		Sequence sequence = mongoTemplate.findAndModify(query, update, options, Sequence.class);
		return sequence.getValue();
	}
}
