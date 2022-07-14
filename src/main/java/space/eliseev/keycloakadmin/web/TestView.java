package space.eliseev.keycloakadmin.web;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.repository.UserRepository;

@Component
@Route
public class TestView extends VerticalLayout {
    private UserRepository userRepository;

    H1 mainHeader = new H1("Тестовая страница");
    H2 adminListHeader = new H2("Все пользователи Keycloak");
    Grid<User> grid;
    TextField filter;
    private Button addNewBtn;
    @Autowired
    public TestView(UserRepository userRepository){
        System.out.println(userRepository.findAll());
        mainHeader.setWidth("50%");
        this.grid = new Grid<>(User.class);
        grid.scrollToStart();
        grid.setItems(userRepository.findAll());
        grid.setColumns("id", "email", "username");
        grid.getColumnByKey("id").setWidth("40%").setFlexGrow(0);
        grid.getColumnByKey("email").setWidth("30%").setFlexGrow(0);
        grid.getColumnByKey("username").setWidth("30%").setFlexGrow(0);
        add(mainHeader, adminListHeader, grid);
    }
}
