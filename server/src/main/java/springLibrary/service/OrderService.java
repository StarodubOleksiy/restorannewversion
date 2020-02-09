package springLibrary.service;

import springLibrary.entities.Orders;
import springLibrary.model.response.OrderResponse;
import springLibrary.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service<Orders, Long, OrderRepository> {

    public List<OrderResponse> findAllResponse();


    public Optional<OrderResponse> findByIdResponse(Long id);

 }
