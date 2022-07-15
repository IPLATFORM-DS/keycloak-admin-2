package space.eliseev.keycloakadmin.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class GetUserForm extends FormLayout {
    TextField username = new TextField("Username");
    Button save = new Button("Найти");
    Button cancel = new Button("Снять фильтры");

    public GetUserForm() {
        username.setWidth("30%");
        save.setWidth("30%");
        cancel.setWidth("30%");
        add(username, save, cancel);
    }
}



