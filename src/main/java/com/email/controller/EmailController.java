package com.email.controller;


import com.email.exceptions.NoDataFoundException;
import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class EmailController
{
    @Value("${UPLOAD_FILE_SIZE}")
    private String maxfilesize;

     @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
   public String welcome()
   {
       return "hello this is my email api";
   }

   @RequestMapping(value="/sendEmail",method = RequestMethod.GET)
   public ResponseEntity<?>  sendEmail(@RequestBody EmailRequest request)throws Exception
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


    @RequestMapping(value="/sendAttach",method = RequestMethod.POST)
    public ResponseEntity<?> sendAttach(@RequestParam("file") MultipartFile file,@RequestParam("to") String to,@RequestParam("message") String message,@RequestParam("subject") String subject)throws Exception
    {

        if (!file.isEmpty()) {
            if(file.getSize() > Integer.parseInt(maxfilesize)) {

                return ResponseEntity.badRequest().body("File Size should be less then 10 MB");
            }

        }

        System.out.println("Max File Size: "+ maxfilesize);

      System.out.println(to);
        boolean result = this.emailService.sendAttach(subject, message,to, file);

        if(result){

            return  ResponseEntity.ok("Sent Email With Attchment Successfully... ");

        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("attachment email sending fail");
        }

    }

   }
