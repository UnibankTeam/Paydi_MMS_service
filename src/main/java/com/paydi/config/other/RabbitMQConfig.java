package com.paydi.config.other;

import java.io.IOException;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.virtual-host}")
	String host;
	@Value("${spring.rabbitmq.username}")
	String username;
	@Value("${spring.rabbitmq.password}")
	String password;

	public static final String QUEUE_CLIENT_ADD = "queue_client_add";
	public static final String EXCHANGE_CLIENT = "exchange_client";

	public static final String QUEUE_CLIENT_POINT = "queue_client_point";
	public static final String EXCHANGE_MAKE_ACCOUNT_COMMAND_TRANSACTION = "exchange_make_account_command_transaction";

	public static final String QUEUE_CREATE_ACCOUNT = "queue_create_account";
	public static final String EXCHANGE_CREATE_ACCOUNT = "exchange_create_account";

	public static final String QUEUE_DONATE_POINT = "queue_donate_point";
	public static final String EXCHANGE_MAKE_DONATE_TRANSACTION = "exchange_make_donate_transaction";

	public static final String QUEUE_BUSINESS_REDEEM = "queue_redeem_business";
	public static final String EXCHANGE_MAKE_BUSINESS_TRANSACTION = "exchange_make_business_transaction";

	public static final String ROUTINGKEY_CLIENT_EVENT = "client_event";

	Binding binding(Queue queue, Exchange exchange, String routingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
	}

	@Bean
	public Declarables directBingdings() {
		Queue directQueueClientAdd = new Queue(QUEUE_CLIENT_ADD, true);
		Queue directQueueClientQueueClientPoint = new Queue(QUEUE_CLIENT_POINT, true);
		Queue directQueueCreateAccount = new Queue(QUEUE_CREATE_ACCOUNT, true);
		Queue directQueueDonatePoint = new Queue(QUEUE_DONATE_POINT, true);
		Queue directQueueBusinessRedeem = new Queue(QUEUE_BUSINESS_REDEEM, true);

		DirectExchange directExchange = new DirectExchange(EXCHANGE_CLIENT);
		DirectExchange exchangeMakeAccountCommandTransaction = new DirectExchange(
				EXCHANGE_MAKE_ACCOUNT_COMMAND_TRANSACTION);
		DirectExchange exchangeCreateAccount = new DirectExchange(EXCHANGE_CREATE_ACCOUNT);
		DirectExchange exchangeMakeDonateTransaction = new DirectExchange(EXCHANGE_MAKE_DONATE_TRANSACTION);
		DirectExchange exchangeMakeBusinessTransaction = new DirectExchange(EXCHANGE_MAKE_BUSINESS_TRANSACTION);

		return new Declarables(directExchange, exchangeMakeAccountCommandTransaction, exchangeCreateAccount,
				exchangeMakeBusinessTransaction, exchangeMakeDonateTransaction,
				binding(directQueueClientAdd, directExchange, "direct.client_add"),
				binding(directQueueClientQueueClientPoint, exchangeMakeAccountCommandTransaction,
						"direct.make.account.transaction"),
				binding(directQueueCreateAccount, exchangeCreateAccount, "direct.create.account"),
				binding(directQueueDonatePoint, exchangeMakeDonateTransaction, "direct.donate.transaction"),
				binding(directQueueBusinessRedeem, exchangeMakeBusinessTransaction, "direct.business.transaction")

		);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	CachingConnectionFactory connectionFactory() throws IOException {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		cachingConnectionFactory.setConnectionTimeout(60 * 1000);
		cachingConnectionFactory.setConnectionLimit(10);
		cachingConnectionFactory.setCloseTimeout(60 * 1000);

		Connection connection = cachingConnectionFactory.createConnection();
		Channel channel = connection.createChannel(true);

		channel.queueDeclare(QUEUE_CLIENT_ADD, true, false, false, null);
		channel.queueDeclare(QUEUE_CLIENT_POINT, true, false, false, null);
		channel.queueDeclare(QUEUE_CREATE_ACCOUNT, true, false, false, null);
		channel.queueDeclare(QUEUE_DONATE_POINT, true, false, false, null);
		channel.queueDeclare(QUEUE_BUSINESS_REDEEM, true, false, false, null);

		channel.exchangeDeclare(EXCHANGE_CLIENT, "direct", true, false, false, null);
		channel.exchangeDeclare(EXCHANGE_MAKE_ACCOUNT_COMMAND_TRANSACTION, "direct", true, false, false, null);
		channel.exchangeDeclare(EXCHANGE_CREATE_ACCOUNT, "direct", true, false, false, null);
		channel.exchangeDeclare(EXCHANGE_MAKE_DONATE_TRANSACTION, "direct", true, false, false, null);
		channel.exchangeDeclare(EXCHANGE_MAKE_BUSINESS_TRANSACTION, "direct", true, false, false, null);

		return cachingConnectionFactory;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {

		return new Jackson2JsonMessageConverter();
	}

}