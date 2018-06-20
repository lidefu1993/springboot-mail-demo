import com.ldf.mail.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by ldf on 2018/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MailTest {

    private String from = "836421293@qq.com";
    private String to = "18354236885@qq.com";//285480629
    private String topPath = System.getProperty("user.dir");
    @Autowired
    public JavaMailSender mailSender;
    @Test
    public void sendSimpleMail(){
        System.out.println(112);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("主题：简单邮件java-springboot-mail");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String imgPath = topPath+"\\Koala.jpg";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("主题：带附件的邮件");
        helper.setText(getHtmlContent(), true);//true表示为html
        FileSystemResource file = new FileSystemResource(new File(imgPath));
        helper.addAttachment("考拉.jpg", file);
        mailSender.send(mimeMessage);
    }

    private String getHtmlContent(){
        return "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
    }


}
