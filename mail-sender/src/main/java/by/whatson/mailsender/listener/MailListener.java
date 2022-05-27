package by.whatson.mailsender.listener;


import by.whatson.mailsender.mail.MailHelper;
import by.whatson.web.dto.NewsForUserMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailListener {
    private final JavaMailSender javaMailSender;

    @JmsListener(concurrency = "3-10", destination = "mail-info-queue")
    private void receiveAndSendMailMessage(NewsForUserMessage queryItem){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(queryItem.getUser().getEmail());
        simpleMailMessage.setText(new MailHelper(queryItem).generateTextForMail());
        javaMailSender.send(simpleMailMessage);
    }
}
