/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.MessageDao;
import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.DatabaseBean;
import com.ship.util.MD5;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 思之声
 */
public class MessageDaoImp implements MessageDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<Message> GetAllMessage() {
        List<Message> messages = new ArrayList<Message>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Message");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();

                message.setMessage_id(rs.getString("Message_id"));
                message.setMessage_name(rs.getString("Message_name"));
                message.setMessage_type(rs.getString("Message_type"));
                message.setMessage_Date(rs.getString("Message_Date"));
                message.setMessage_title(rs.getString("Message_title"));
                message.setMessage_detail(rs.getString("Message_detail"));
                message.setMessage_state(rs.getString("Message_state"));

                messages.add(message);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return messages;
    }

    @Override
    public List<Message> GetMessageByMessageType(String messageType) {
        List<Message> messages = new ArrayList<>();
        List<Message> allMessages = GetAllMessage();
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).getMessage_type().equals(messageType)) {
                messages.add(allMessages.get(i));
            }
        }

        return messages;
    }

    @Override
    public Message GetAMessageByByMessageId(String messageId) {
        Message message = new Message();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Message where Message_id = ?");
            psmt.setString(1, messageId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                message.setMessage_id(rs.getString("Message_id"));
                message.setMessage_name(rs.getString("Message_name"));
                message.setMessage_type(rs.getString("Message_type"));
                message.setMessage_Date(rs.getString("Message_Date"));
                message.setMessage_title(rs.getString("Message_title"));
                message.setMessage_detail(rs.getString("Message_detail"));
                message.setMessage_state(rs.getString("Message_state"));
                return message;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean AddMessage(Message message) {
        try {
            String sql = "insert into Message values(?,?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, message.getMessage_id());
            psmt.setString(2, message.getMessage_name());
            psmt.setString(3, message.getMessage_type());
            psmt.setString(4, message.getMessage_Date());
            psmt.setString(5, message.getMessage_title());
            psmt.setString(6, message.getMessage_detail());
            psmt.setString(7, message.getMessage_state());

            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteMessage(String messageId) {
        try {
            String sql = "delete from Message where Message_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, messageId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean ChangeState(String messageId) {
        try {
            String sql = "update Message set Message_state = ? where Message_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "通过");
            psmt.setString(2, messageId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
