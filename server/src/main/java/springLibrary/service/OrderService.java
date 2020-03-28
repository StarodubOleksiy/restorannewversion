package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.entities.Orders;
import springLibrary.model.response.OrderResponse;
import springLibrary.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service<Orders, Long, OrderRepository> {

    public List<OrderResponse> findAllResponse();

    public void save(Orders order);

    public Optional<OrderResponse> findByIdResponse(Long id);

    public List<OrderResponse> findOrdersByDate(String date);

 }
