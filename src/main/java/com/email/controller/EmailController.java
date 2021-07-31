package com.email.controller;


import com.email.exceptions.ErrorObject;
import com.email.exceptions.NoDataFoundException;
import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
       if(!result) {
           throw new NoDataFoundException("Email Not Valid");
       }
       if(result) {
           ErrorObject errorObj = new ErrorObject();
           errorObj.setTimeStamp(67677777);
           errorObj.setMessage("Email Sent Successfully");
           errorObj.setStatusCode(200);
           return ResponseEntity.ok(errorObj);

     } else {
           ErrorObject errorObj = new ErrorObject();
           errorObj.setTimeStamp(67677777);
           errorObj.setMessage("Email not sent");
           errorObj.setStatusCode(404);
           return ResponseEntity.internalServerError().body(errorObj);

          }
       }


    //this api send email with file
    @RequestMapping(value="/sendAttach",method = RequestMethod.POST)
    public ResponseEntity<?> sendAttach(@RequestParam("file") MultipartFile file,@RequestParam("to") String to,@RequestParam("message") String message,@RequestParam("subject") String subject)throws Exception
    {
        if (!file.isEmpty()) {
            if (file.getSize() > Integer.parseInt(maxfilesize)) {
                ErrorObject errorObj = new ErrorObject();
                errorObj.setTimeStamp(67677777);
                errorObj.setMessage("File Size should be less then 10 MB.");
                errorObj.setStatusCode(400);
                return ResponseEntity.badRequest().body(errorObj);
            }
        }
        boolean result = this.emailService.sendAttach(subject, message,to, file);
        if(!result) {
            throw new NoDataFoundException("Email Not Valid");
        }
        if(result) {
            ErrorObject errorObj = new ErrorObject();
            errorObj.setTimeStamp(67677777);
            errorObj.setMessage("Sent Email With Attchment Successfully...");
            errorObj.setStatusCode(200);
            return ResponseEntity.ok(errorObj);
        } else{
            ErrorObject errorObj = new ErrorObject();
            errorObj.setTimeStamp(67677777);
            errorObj.setMessage("attachment email sending fail");
            errorObj.setStatusCode(404);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorObj);
        }

    }

   }
