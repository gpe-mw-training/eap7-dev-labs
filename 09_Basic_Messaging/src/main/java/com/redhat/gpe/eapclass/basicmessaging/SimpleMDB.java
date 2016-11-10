package com.redhat.gpe.eapclass.basicmessaging;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SimpleMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(SimpleMDB.class.toString());

    public void onMessage(Message rcvMessage) {
    }
}
