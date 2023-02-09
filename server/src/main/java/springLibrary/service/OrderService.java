package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.entities.Orders;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.request.OrderRequest;
import springLibrary.model.response.OrderResponse;
import springLibrary.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service<Orders, Long, OrderRepository> {

    public List<OrderResponse> findAllResponse();

    public Optional<OrderResponse> findByIdResponse(Long id);

    public List<OrderResponse> findOrdersByDate(String date);

    public void deleteOrderById(long id);

    public void saveFromRequest(OrderRequest orderRequest);

    public void updateFromRequest(OrderRequest orderRequest);

 }
