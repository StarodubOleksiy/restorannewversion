package springLibrary.service;

import springLibrary.entities.Employee;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends Service<Employee, Long, EmployeeRepository> {


    public List<EmployeeResponse> findAllResponse();

    public Optional<EmployeeResponse> findByIdResponse(Long id);

}
