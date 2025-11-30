# Java Stream API – Employee CSV Processor

A clean Java 17+ demo that processes 1000+ employee records using **only the modern Stream API** (no traditional loops).

### Features
- Generates 1000 random employees → `list_of_employees.csv`
- Loads CSV into `List<Employe>`
- All operations done with streams & lambdas:
    - Filter by department
    - Average salary
    - Highest paid employees
    - Total payroll (reduce)
    - Count employees per department (`groupingBy` + `counting`)
    - Complex query: salary > 50k **AND** age < 40
    - Exports result to new CSV file

### Output files
- `list_of_employees.csv` : source data (1000 rows)
- `high_earners_under_40.csv` : filtered result

### How to run
1. Run `CSVmain` : creates the 1000-employee CSV on your Desktop
2. Run `EmployeeProcessor` : loads data, runs all queries and exports the result