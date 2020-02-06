package springLibrary.service;

import springLibrary.entities.Employee;
import springLibrary.model.request.AuthorRequest;
import springLibrary.model.response.AuthorResponse;
import springLibrary.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends Service<Employee, Long, EmployeeRepository> {


    public List<AuthorResponse> findAllResponse();

    public Optional<AuthorResponse> findByIdResponse(Long id);

}
