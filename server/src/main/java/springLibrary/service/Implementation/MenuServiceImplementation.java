package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Dish;
import springLibrary.entities.Menu;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.MenuResponse;
import springLibrary.repository.MenuRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.MenuService;

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
    public List<MenuResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::menuToMenuResponse)
                .collect(Collectors.toList());
    }



    @Override
    public Optional<MenuResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::menuToMenuResponse);
    }

    @Override
    public void deleteMenu(Long id) {
        getRepository().deleteById(id);
    }



    @Override
    public void save(Menu menu) {
        //if(menu.getId() == 0)
            super.save(menu);
       /* else {
            if (getRepository().getOne(menu.getId()).getBooks() != null) ;
            menu.setBooks(getRepository().getOne(menu.getId()).getBooks());
            super.save(menu);
        }*/

    }


}

