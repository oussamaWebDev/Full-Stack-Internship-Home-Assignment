package ma.dnaengineering.backend.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ma.dnaengineering.backend.Employee.Employee;
import ma.dnaengineering.backend.Employee.EmployeeDetails;
import ma.dnaengineering.backend.Employee.JobSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    public ResponseEntity<Object> getDetails(MultipartFile file) {
        try {
            List<Employee> emp=extractEmploy(file);
            EmployeeDetails res=new EmployeeDetails(emp,calculateAverageSalaryByJobTitle(emp));
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public static  List<Employee> extractEmploy(MultipartFile file){

        List<Employee> employeeList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] nextRecord;

            // Skip header if present
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                Employee employee = new Employee();
                employee.setId(Long.parseLong(nextRecord[0]));
                employee.setEmployeeName(nextRecord[1]);
                employee.setJobTitle(nextRecord[2]);
                employee.setSalary(Double.parseDouble(nextRecord[3]));

                employeeList.add(employee);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return  employeeList;
    }
    public static List<JobSummary> calculateAverageSalaryByJobTitle(List<Employee> employees) {
        Map<String, Double> jobTitleToTotalSalary = new HashMap<>();
        Map<String, Integer> jobTitleToCount = new HashMap<>();

        for (Employee employee : employees) {
            String jobTitle = employee.getJobTitle();
            double salary = employee.getSalary();

            // Update total salary and count for the job title
            jobTitleToTotalSalary.put(jobTitle, jobTitleToTotalSalary.getOrDefault(jobTitle, 0.0) + salary);
            jobTitleToCount.put(jobTitle, jobTitleToCount.getOrDefault(jobTitle, 0) + 1);
        }

        List<JobSummary> jobSummaries = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        // Calculate average salary for each job title
        for (Map.Entry<String, Double> entry : jobTitleToTotalSalary.entrySet()) {
            String jobTitle = entry.getKey();
            double totalSalary = entry.getValue();
            int count = jobTitleToCount.get(jobTitle);

            double averageSalary = totalSalary / count;
            Double formattedAverageSalary = Double.valueOf(decimalFormat.format(averageSalary));
            jobSummaries.add(new JobSummary(jobTitle, formattedAverageSalary));
        }

        return jobSummaries;
    }
}
