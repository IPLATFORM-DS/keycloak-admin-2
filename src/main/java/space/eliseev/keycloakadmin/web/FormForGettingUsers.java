package space.eliseev.keycloakadmin.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class FormForGettingUsers extends FormLayout {
    TextField id = new TextField("ID");
    Button save = new Button("Найти");
    Button cancel = new Button("Снять фильтры");

    public FormForGettingUsers() {
        id.setWidth("100%");
        save.setWidth("20%");
        cancel.setWidth("50%");
        add(id, save, cancel);
    }
}



