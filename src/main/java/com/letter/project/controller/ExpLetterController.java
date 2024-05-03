package com.letter.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.letter.project.model.ExpLetter;
import com.letter.project.service.ExpLetterService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/expLetter")
public class ExpLetterController {
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	@Autowired
	private JavaMailSender jms;
	

	@Autowired
	private ExpLetterService expLetterService;
	
	@PostMapping("/addData")
	public ExpLetter addData(@RequestBody ExpLetter expLetter) {
		return expLetterService.addData(expLetter);
		
	}
	
	@GetMapping("/getAllData")
	public List<ExpLetter>  getAllData(){
		return expLetterService.getAllData();
		
	}
	

	@PostMapping("/sendEmail")
	public String sendEmail(@RequestParam("email") String email,
	                        @RequestParam("sub") String sub,
	                        @RequestParam("msg") String msg,
	                        @RequestParam("imageData") String imageData) {
	    try {
	        byte[] decodedImage = Base64.getDecoder().decode(imageData.split(",")[1]);
	        File pngFile = new File("ExperienceLetter.png");
	        FileOutputStream outputStream = new FileOutputStream(pngFile);
	        outputStream.write(decodedImage);
	        outputStream.close();
	        
	        // Convert PNG to PDF
	        File pdfFile = new File("ExperienceLetter.pdf");
	        convertImageToPdf(pngFile, pdfFile);
	        
	        sendEmailWithAttachment(email, pdfFile, sub, msg);
	        
	        // Clean up files if needed
	        pngFile.delete();
	        pdfFile.delete();
	        
	        return "Email sent successfully!!";
	    } catch (IOException | MessagingException | DocumentException e) {
	        e.printStackTrace();
	        return "Failed to send!!!";
	    }
	}


	private void convertImageToPdf(File imageFile, File pdfFile) throws DocumentException, IOException {
	    Document document = new Document(PageSize.A4);
	    PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
	    document.open();
	    Image image = Image.getInstance(imageFile.getAbsolutePath());

	    // Calculate scaling to fit the image within the page
	    float imageWidth = image.getWidth();
	    float imageHeight = image.getHeight();
	    float pageWidth = PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin();
	    float pageHeight = PageSize.A4.getHeight() - document.topMargin() - document.bottomMargin();

	    float widthScale = pageWidth / imageWidth;
	    float heightScale = pageHeight / imageHeight;

	    float scale = Math.min(widthScale, heightScale);

	    image.scaleToFit(imageWidth * scale, imageHeight * scale);

	    // Add image to the document
	    document.add(image);
	    document.close();
	}


	private void sendEmailWithAttachment(String email, File file, String sub, String msg)
	        throws MessagingException {
	    // Prepare MimeMessage
	    MimeMessage mimeMessage = jms.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	    helper.setFrom(fromMail);
	    helper.setTo(email);
	    helper.setSubject(sub);
	    helper.setText(msg);

	    // Attach the PDF file to the email
	    helper.addAttachment("ExperienceLetter.pdf", file);

	    // Send the email
	    jms.send(mimeMessage);
	}

}
