package com.anton.expo.commands.mail;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class SendMailCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        double total = (double) session.getAttribute("total");
        User user = (User) session.getAttribute("user");

        Properties properties = ServiceFactory.getEmailService().loadProperties();
        Session mailSession = Session.getDefaultInstance(properties);
        resp.setContentType("text/html");

        try {
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.host")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Tickets from Expo Center");
            message.setContent(ServiceFactory.getEmailService().createEmailText(cart, total, user), "text/html");

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        cart.clear();
        session.setAttribute("cart", null);
        session.setAttribute("total", null
        );

        return new String[] {"", "redirect"};
    }
}
