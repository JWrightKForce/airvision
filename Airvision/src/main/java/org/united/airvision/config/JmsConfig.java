package org.united.airvision.config;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;

@Configuration
@EnableJms
@ComponentScan
public class JmsConfig {



    @Bean(value = "mqConnectionFactory",autowireCandidate = true)
    public ActiveMQConnectionFactory mqConnectionFactory(){
        ActiveMQConnectionFactory mqConnectionFactory=new ActiveMQConnectionFactory();
        mqConnectionFactory.setTrustAllPackages(true);
        mqConnectionFactory.setBrokerURL("tcp://localhost:61616");
        mqConnectionFactory.setUserName("admin");
        mqConnectionFactory.setPassword("admin");
        return mqConnectionFactory;
    }
    @Bean(name="mqSeriesContainerFactory", autowireCandidate = false)
    public SimpleJmsListenerContainerFactory mqSeriesContainerFactory(){

        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        System.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
        System.setProperty(Context.SECURITY_PRINCIPAL,"admin");
        System.setProperty(Context.SECURITY_CREDENTIALS,"admin");

        SimpleJmsListenerContainerFactory jmsListenerContainerFactory=new SimpleJmsListenerContainerFactory();
        try{
            InitialContext ctx = new InitialContext();
            System.out.println(" Bean Context Initialized");
            ConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            jmsListenerContainerFactory.setConnectionFactory(queueConnectionFactory);
            jmsListenerContainerFactory.setPubSubDomain(false);
            jmsListenerContainerFactory.setAutoStartup(true);
            System.out.println(" MQ Container Created");
            return jmsListenerContainerFactory;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
