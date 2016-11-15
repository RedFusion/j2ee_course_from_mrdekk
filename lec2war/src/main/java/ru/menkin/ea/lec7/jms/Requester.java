package ru.menkin.ea.lec7.jms;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.menkin.ea.lec7.model.entities.ProductHolder;
import ru.menkin.ea.lec7.model.requests.ProductsRequest;

public class Requester
{
	private static final Logger log = LoggerFactory.getLogger(Requester.class);

	private JmsTemplate _jmsTemplate;
	private Destination _destination;

	// @formatter:off
	public JmsTemplate getJmsTemplate() { return _jmsTemplate; }
	@Required
	public void setJmsTemplate(JmsTemplate jmsTemplate) { _jmsTemplate = jmsTemplate; }
	
	public Destination getDestination() { return _destination; }
	@Required
	public void setDestination(Destination destination) { _destination = destination; }
	// @formatter:on

	public void request()
	{
		try
		{
			ProductHolder warehouse = new ProductHolder();
			
			ProductsRequest request = new ProductsRequest();
			request.setType("products.request");
			request.setLimit(10);
			request.setHolder(warehouse);

			ObjectMapper jsonMapper = new ObjectMapper();
			final String json = jsonMapper.writeValueAsString(request);
			final byte[] jsonb = json.getBytes("UTF-8");
			
			_jmsTemplate.send(_destination, new MessageCreator()
			{
				@Override
				public Message createMessage(Session session) throws JMSException
				{
					BytesMessage msg = session.createBytesMessage();
					msg.writeBytes(jsonb);
					
					return msg;
				}
			});
		} 
		catch (Exception e)
		{
			log.info("Requester.class exception");
		}
	}
}