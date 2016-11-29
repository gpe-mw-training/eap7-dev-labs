package com.redhat.gpe.eapclass.basicmessaging;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "simpleMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/gpteQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class SimpleMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(SimpleMDB.class.toString());

    public void onMessage(Message rcvMessage) {
	TextMessage msg = null;
	try {
    		if (rcvMessage instanceof TextMessage) {
        		msg = (TextMessage) rcvMessage;
        		LOGGER.info("Received Message from queue: " + msg.getText());
    		} else {
       			 LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
    		}
	} catch (JMSException e) {
    		throw new RuntimeException(e);
	}
    }
}
