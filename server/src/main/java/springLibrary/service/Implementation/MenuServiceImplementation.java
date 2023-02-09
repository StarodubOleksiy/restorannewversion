package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Dish;
import springLibrary.entities.Menu;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.MenuResponse;
import springLibrary.repository.MenuRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.MenuService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImplementation extends AbstractService<Menu, Long, MenuRepository> implements MenuService {

    protected MenuServiceImplementation(@Autowired MenuRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImplementation.class);

    private MenuResponse menuToMenuResponse(Menu menu) {
        MenuResponse response = new MenuResponse();
        response.setId(menu.getId());
        response.setName(menu.getName());
        return response;
    }


    @Override
    @Transactional
    public List<MenuResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::menuToMenuResponse)
                .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public Optional<MenuResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::menuToMenuResponse);
    }

    @Override
    @Transactional
    public void saveFromRequest(MenuRequest menuRequest)
    {
        Menu menu = menuRequest.toMenu();
        getRepository().save(menu);
    }

    @Override
    @Transactional
    public void updateFromRequest(MenuRequest menuRequest)
    {
        Menu menu = getOne(menuRequest.getId());
        menu.setName(menuRequest.getName());
        getRepository().save(menu);
    }


}

