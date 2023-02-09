package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.entities.OrderStatus;
import springLibrary.entities.Orders;
import springLibrary.entities.Waiter;
import springLibrary.model.request.OrderRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.OrderResponse;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.EmployeeService;
import springLibrary.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation extends AbstractService<Orders, Long, OrderRepository> implements OrderService {

    protected OrderServiceImplementation(@Autowired OrderRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImplementation.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id=%d";

    LocalDate date = LocalDate.now();

    @Autowired
    private EmployeeService employeeService;

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


    @Override
    @Transactional
    public void deleteOrderById(long id) {
        // getRepository().deleteById(id); //Todo this method does not work and I do not know why.
        LOGGER.info("this.getOne(id).getState().getStatus() ===!!!" + this.getOne(id).getState().getStatus());
        jdbcTemplate.execute(String.format(SQL_DELETE_ORDER,id));
    }

    @Override
    @Transactional
    public void saveFromRequest(OrderRequest orderRequest)
    {
        Orders order = orderRequest.toOrder();
        order.setWaiter(new Waiter(employeeService.getOne(orderRequest.getWaiterId())));
        getRepository().save(order);
    }

    @Override
    @Transactional
    public void updateFromRequest(OrderRequest orderRequest)
    {
        Orders order = getOne(orderRequest.getId());
        order.setTableNumber(Integer.parseInt(orderRequest.getTableNumber()));
        order.setWaiter(new Waiter(employeeService.getOne(orderRequest.getWaiterId())));
        order.setOrderDate(date.toString());
        order.setState(OrderStatus.open);
        getRepository().save(order);
    }


}
