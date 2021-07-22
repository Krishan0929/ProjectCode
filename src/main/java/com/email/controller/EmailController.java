package com.email.controller;


import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class EmailController
{
    //@Value("${spring.servelet.multipart.max-file-size}")
   // private String maxfilesize;
   // private Environment env;
     @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
   public String welcome()
   {
       return "hello this is my email api";
   }

   @RequestMapping(value="/sendEmail",method = RequestMethod.POST)
   public ResponseEntity<?>  sendEmail(@RequestBody EmailRequest request)
   {
       boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
       System.out.println(request);
       if(result) {
           return ResponseEntity.ok("Email is sent successfuly");

   }
       else {

           return ResponseEntity.internalServerError().body("Email not sent");
       }
       }
    //this api send email with file
   // @PostMapping("/sendAttach")
    @RequestMapping(value="/sendAttach",method = RequestMethod.POST)
    public ResponseEntity<?> sendAttach(@RequestBody EmailRequest request)
    {
       // String maxFileSize=env.getProperty("spring.servelet.multipart.max-file-size");
        //System.out.println("Max File Size: "+maxFileSize);
        System.out.println(request);

        boolean result = this.emailService.sendAttach(request.getSubject(), request.getMessage(), request.getTo());

        if(result){

            return  ResponseEntity.ok("Sent Email With Attchment Successfully... ");

        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("attachment email sending fail");
        }

    }

   }
