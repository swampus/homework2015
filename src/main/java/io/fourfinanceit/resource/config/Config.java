package io.fourfinanceit.resource.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory(HibernateEntityManagerFactory factory) {
		return factory.getSessionFactory();
	}

}
