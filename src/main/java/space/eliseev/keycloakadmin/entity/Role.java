package space.eliseev.keycloakadmin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "keycloak_role")
public class Role extends BaseEntity {

    @Column(name = "client_realm_constraint")
    private String clientRealmConstraint;

    @Column(name = "client_role", nullable = false)
    private Boolean clientRole = false;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "realm_id")
    private String realmId;

    @Column(name = "client", length = 36)
    private String client;

    @Column(name = "realm", length = 36)
    private String realm;

}
