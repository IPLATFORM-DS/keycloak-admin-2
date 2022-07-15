package space.eliseev.keycloakadmin.web;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Route
public class TestView extends VerticalLayout {
    private UserRepository userRepository;
    H1 mainHeader = new H1("Тестовая страница");
    H2 adminListHeader = new H2("Все пользователи Keycloak");
    GetUserForm getUserForm = new GetUserForm();
    Grid<User> grid;

    @Autowired
    public TestView(UserRepository userRepository){
        this.userRepository = userRepository;
        mainHeader.setWidth("50%");
        getUserForm.save.addClickListener(e -> updateList());
        getUserForm.cancel.addClickListener(e -> grid.setItems(userRepository.findAll()));
        getUserForm.setWidth("50%");
        this.grid = new Grid<>(User.class);
        grid.scrollToStart();
        grid.setItems(userRepository.findAll());
        grid.setColumns("id", "email", "username");
        grid.getColumnByKey("id").setWidth("30%").setFlexGrow(0);
        grid.getColumnByKey("email").setWidth("30%").setFlexGrow(0);
        grid.getColumnByKey("username").setWidth("30%").setFlexGrow(0);
        add(mainHeader, getUserForm, adminListHeader, grid);
    }

    private void updateList() {
        Optional<User> a;
        if (getUserForm.username.getValue() != null && !getUserForm.username.getValue().equals("")) {
            if ((a = userRepository.findByUsername(getUserForm.username.getValue())).isPresent()) {
                grid.setItems(a.get());
            } else {
                grid.setItems(new ArrayList<>());
            }
        } else {
            grid.setItems(userRepository.findAll());
        }
    }
}
