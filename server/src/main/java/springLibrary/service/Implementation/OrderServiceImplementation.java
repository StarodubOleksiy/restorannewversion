package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.entities.OrderStatus;
import springLibrary.entities.Orders;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.OrderResponse;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation extends AbstractService<Orders, Long, OrderRepository> implements OrderService {

    protected OrderServiceImplementation(@Autowired OrderRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImplementation.class);

    private OrderResponse orderToOrderResponse(Orders order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setTableNumber(order.getTableNumber());
        response.setWaiterId(order.getWaiter().getId());
        response.setWaiterName(order.getWaiter().getName());
        response.setWaiterSurname(order.getWaiter().getSurname());
        response.setOrderDate(order.getOrderDate());
        response.setState(OrderStatus.enumToString(order.getState()));// response.setState(OrderStatus.enumToString(order.getState()));
        return response;
    }


    @Override
    public List<OrderResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::orderToOrderResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<OrderResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::orderToOrderResponse);
    }

    @Override
    public void save(Orders order) {
        super.save(order);

    }

    @Override
    public List<OrderResponse> findOrdersByDate(String date) {
        return getRepository().findByDate(date).stream()
                .map(this::orderToOrderResponse)
                .collect(Collectors.toList());
    }


}
