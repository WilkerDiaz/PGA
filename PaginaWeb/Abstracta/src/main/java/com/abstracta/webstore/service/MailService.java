package com.abstracta.webstore.service;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private VelocityEngine velocityEngine;
    
    public void setMailSender(JavaMailSender mailSender){
    	this.mailSender = mailSender;
    }
    
    public void sendMail(final Map<String, Object> miniModel, final String from, final String to, final String subject, 
    			final String template, final String attachmentPath, final String attachmentName) {
        
        /*SimpleMailMessage message = new SimpleMailMessage();
         
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);*/
    	
    	MimeMessagePreparator preparator = new MimeMessagePreparator() {
    		 
            public void prepare(MimeMessage mimeMessage) throws Exception {
 
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(to);
                message.setFrom(new InternetAddress(from));
                message.setSubject(subject);
 
                @SuppressWarnings("unchecked")
				String body = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "/mail/"+ template +".vm", "ISO-8859-1", miniModel);
                message.setText(body, true);
 
                if (!StringUtils.isBlank(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    message.addAttachment(attachmentName, file);
                }
            }
        };
        
        this.mailSender.send(preparator);
    }
    
}
