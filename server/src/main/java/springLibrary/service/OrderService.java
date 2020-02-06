package springLibrary.service;

import springLibrary.entities.Orders;
import springLibrary.model.response.GenreResponse;
import springLibrary.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service<Orders, Long, OrderRepository> {

    public List<GenreResponse> findAllResponse();


    public Optional<GenreResponse> findByIdResponse(Long id);

 }
