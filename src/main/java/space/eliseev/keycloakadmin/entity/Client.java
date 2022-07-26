package space.eliseev.keycloakadmin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "client")
public class Client extends BaseEntity {

    @Column (name = "enabled")
    private Boolean enabled;

    @Column (name = "full_scope_allowed")
    private Boolean fullScopeAllowed;

    @Column (name = "client_id")
    private String clientId;

    @Column (name = "not_before")
    private Integer notBefore;

    @Column (name = "public_client")
    private Boolean publicClient;

    @Column (name = "secret")
    private String secret;

    @Column (name = "base_url")
    private String baseUrl;

    @Column (name = "bearer_only")
    private Boolean bearerOnly;

    @Column (name = "management_url")
    private String managementUrl;

    @Column (name = "surrogate_auth_required")
    private Boolean surrogateAuthRequired;

    @Column (name = "realm_id")
    private String realmId;

    @Column (name = "protocol")
    private String protocol;

    @Column (name = "node_rereg_timeout")
    private Integer nodeReregTimeout;

    @Column (name = "frontchannel_logout")
    private Boolean frontchannelLogout;

    @Column (name = "consent_required")
    private Boolean consentRequired;

    @Column (name = "name")
    private String name;

    @Column (name = "service_accounts_enabled")
    private Boolean serviceAccountsEnabled;

    @Column (name = "client_authenticator_type")
    private String clientAuthenticatorType;

    @Column (name = "root_url")
    private String rootUrl;

    @Column (name = "description")
    private String description;

    @Column (name = "registration_token")
    private String registrationToken;

    @Column (name = "standard_flow_enabled")
    private Boolean standardFlowEnabled;

    @Column (name = "implicit_flow_enabled")
    private Boolean implicitFlowEnabled;

    @Column (name = "direct_access_grants_enabled")
    private Boolean directAccessGrantsEnabled;

    @Column (name = "always_display_in_console")
    private Boolean alwaysDisplayInConsole;

}
