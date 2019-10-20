/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao;

import com.ship.model.Message;
import java.util.List;

public interface MessageDao {

    public List<Message> GetAllMessage();

    public List<Message> GetMessageByMessageType(String messageType);

    public Message GetAMessageByByMessageId(String messageId);

    public boolean AddMessage(Message message);

    public boolean DeleteMessage(String messageId);

    public boolean ChangeState(String messageId);
}
