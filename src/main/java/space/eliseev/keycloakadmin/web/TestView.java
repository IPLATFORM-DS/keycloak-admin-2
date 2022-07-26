package space.eliseev.keycloakadmin.web;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

@Route
public class TestView extends VerticalLayout {
    private final UserService userService;
    private final FormForGettingUsers formForGettingUsers = new FormForGettingUsers();
    private final Grid<UserDto> grid = new Grid<>(UserDto.class);

    public TestView(@Autowired UserService userService) {
        this.userService = userService;
        init();
    }

    private void init() {
        H1 mainHeader = new H1("Тестовая страница");
        H2 adminListHeader = new H2("Все пользователи Keycloak");
        setFormForGettingUsers();
        setUserGrid();
        add(mainHeader, formForGettingUsers, adminListHeader, grid);
    }

    private void setFormForGettingUsers() {
        formForGettingUsers.save.addClickListener(e -> updateList());
        formForGettingUsers.cancel.addClickListener(e -> grid.setItems(userService.getAllUsers()));
        formForGettingUsers.setWidth("50%");
    }

    private void setUserGrid() {
        grid.scrollToStart();
        grid.setItems(userService.getAllUsers());
        grid.setColumns("email", "emailVerified", "firstName", "lastName", "username");
        grid.getColumnByKey("email").setWidth("40%").setFlexGrow(0);
        grid.getColumnByKey("emailVerified").setWidth("10%").setFlexGrow(0);
        grid.getColumnByKey("firstName").setWidth("15%").setFlexGrow(0);
        grid.getColumnByKey("lastName").setWidth("15%").setFlexGrow(0);
        grid.getColumnByKey("username").setWidth("20%").setFlexGrow(0);
    }

    private void updateList() {
        if (!formForGettingUsers.id.getValue().equals("")) {
            Optional<UserDto> a = userService.getById(formForGettingUsers.id.getValue());
            if (a.isPresent()) {
                grid.setItems(a.get());
            } else {
                grid.setItems(new ArrayList<>());
            }
        } else {
            grid.setItems(userService.getAllUsers());
        }
    }
}
