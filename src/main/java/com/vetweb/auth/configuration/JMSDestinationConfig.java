package com.vetweb.auth.configuration;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
		name = "java:/jms/topics/EmailSender",
		interfaceName = "javax.jms.Topic"
)
@Singleton
public class JMSDestinationConfig {

}
