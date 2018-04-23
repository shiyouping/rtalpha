package com.rtalpha.base.web.security;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.base.mongo.document.Customer;

/**
 * Verify user access from MongoDB
 * 
 * @author Ricky
 * @since Apr 25, 2017
 */
@Service
@RefreshScope
public class MongoCredentialServiceImpl implements UserDetailsService {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public MongoCredentialServiceImpl(@Nonnull MongoTemplate mongoTemplate) {
		checkNotNull(mongoTemplate, "mongoTemplate cannot be null");
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		StringBuilder role = new StringBuilder("ROLE_");

		Query query = Query.query(Criteria.where("email").is(username));
		Customer customer = mongoTemplate.findOne(query, Customer.class);
		Agent agent = mongoTemplate.findOne(query, Agent.class);

		if (customer == null && agent == null) {
			throw new UsernameNotFoundException(String.format("Cannot find username=%s", username));
		}

		if (customer != null) {
			role.append("CUSTOMER");
			GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			user = new User(customer.getEmail(), customer.getPassword(), customer.getIsActive(), true, true, true,
					Lists.newArrayList(authority));
		} else {
			role.append("AGENT");
			GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			user = new User(agent.getEmail(), agent.getPassword(), agent.getIsActive(), true, true, true,
					Lists.newArrayList(authority));
		}

		return user;
	}
}
