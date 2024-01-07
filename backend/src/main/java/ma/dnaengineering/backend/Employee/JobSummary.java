package ma.dnaengineering.backend.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSummary {
    private String jobTitle;
    private double averageSalary;
}
