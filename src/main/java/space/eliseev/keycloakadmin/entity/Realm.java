package space.eliseev.keycloakadmin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "realm")
public class Realm extends BaseEntity {

    @Column(name = "access_code_lifespan")
    private Integer accessCodeLifespan;

    @Column(name = "user_action_lifespan")
    private Integer userActionLifespan;

    @Column(name = "access_token_lifespan")
    private Integer accessTokenLifespan;

    @Column(name = "account_theme")
    private String accountTheme;

    @Column(name = "admin_theme")
    private String adminTheme;

    @Column(name = "email_theme")
    private String emailTheme;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "events_enabled")
    private boolean eventsEnabled;

    @Column(name = "events_expiration")
    private Long eventsExpiration;

    @Column(name = "login_theme")
    private String loginTheme;

    @Column(name = "name")
    private String name;

    @Column(name = "not_before")
    private Integer notBefore;

    @Column(name = "password_policy")
    private String passwordPolicy;

    @Column(name = "registration_allowed")
    private boolean registrationAllowed;

    @Column(name = "remember_me")
    private boolean rememberMe;

    @Column(name = "reset_password_allowed")
    private boolean resetPasswordAllowed;

    @Column(name = "social")
    private boolean social;

    @Column(name = "ssl_required")
    private String sslRequired;

    @Column(name = "sso_idle_timeout")
    private Integer ssoIdleTimeout;

    @Column(name = "sso_max_lifespan")
    private Integer ssoMaxLifespan;

    @Column(name = "update_profile_on_soc_login")
    private boolean updateProfileOnSocLogin;

    @Column(name = "verify_email")
    private boolean verifyEmail;

    @Column(name = "master_admin_client")
    private String masterAdminClient;

    @Column(name = "login_lifespan")
    private Integer loginLifespan;

    @Column(name = "internationalization_enabled")
    private boolean internationalizationEnabled;

    @Column(name = "default_locale")
    private String defaultLocale;

    @Column(name = "reg_email_as_username")
    private boolean regEmailAsUsername;

    @Column(name = "admin_events_enabled")
    private boolean adminEventsEnabled;

    @Column(name = "admin_events_details_enabled")
    private boolean adminEventsDetailsEnabled;

    @Column(name = "edit_username_allowed")
    private boolean editUsernameAllowed;

    @Column(name = "otp_policy_counter")
    private Integer otpPolicyCounter;

    @Column(name = "otp_policy_window")
    private Integer otpPolicyWindow;

    @Column(name = "otp_policy_period")
    private Integer otpPolicyPeriod;

    @Column(name = "otp_policy_digits")
    private Integer otpPolicyDigits;

    @Column(name = "otp_policy_alg")
    private String otpPolicyAlg;

    @Column(name = "otp_policy_type")
    private String otpPolicyType;

    @Column(name = "browser_flow")
    private String browserFlow;

    @Column(name = "registration_flow")
    private String registrationFlow;

    @Column(name = "direct_grant_flow")
    private String directGrantFlow;

    @Column(name = "reset_credentials_flow")
    private String resetCredentialsFlow;

    @Column(name = "client_auth_flow")
    private String clientAuthFlow;

    @Column(name = "offline_session_idle_timeout")
    private Integer offlineSessionIdleTimeout;

    @Column(name = "revoke_refresh_token")
    private boolean revokeRefreshToken;

    @Column(name = "access_token_life_implicit")
    private Integer accessTokenLifeImplicit;

    @Column(name = "login_with_email_allowed")
    private boolean loginWithEmailAllowed;

    @Column(name = "duplicate_emails_allowed")
    private boolean duplicateEmailsAllowed;

    @Column(name = "docker_auth_flow")
    private String dockerAuthFlow;

    @Column(name = "refresh_token_max_reuse")
    private Integer refreshTokenMaxReuse;

    @Column(name = "allow_user_managed_access")
    private boolean allowUserManagedAccess;

    @Column(name = "sso_max_lifespan_remember_me")
    private int ssoMaxLifespanRememberMe;

    @Column(name = "sso_idle_timeout_remember_me")
    private int ssoIdleTimeoutRememberMe;
    
    @Column(name = "default_role")
    private String defaultRole;
}
