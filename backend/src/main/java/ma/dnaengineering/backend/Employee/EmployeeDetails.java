package ma.dnaengineering.backend.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetails {
    private List<Employee> employees;
    private List<JobSummary> jobSummaries;
}
