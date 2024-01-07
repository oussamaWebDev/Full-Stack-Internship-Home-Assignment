package ma.dnaengineering.backend.controller;

import lombok.AllArgsConstructor;
import ma.dnaengineering.backend.Employee.Employee;
import ma.dnaengineering.backend.Employee.EmployeeDetails;
import ma.dnaengineering.backend.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeProcessController {
    private EmployeeService employeeService;
    @PostMapping("/process")

      public ResponseEntity<Object> processCSVFile(@RequestParam("file") MultipartFile file){
           try {
               if (file.isEmpty()) {
                   return new ResponseEntity<>("Please select a file to upload",HttpStatus.BAD_REQUEST);
               }
               return new ResponseEntity<>(employeeService.getDetails(file), HttpStatus.OK);

           }catch (Exception ex){
               ex.printStackTrace();
           }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
      }

}
