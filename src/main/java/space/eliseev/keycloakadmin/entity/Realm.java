package space.eliseev.keycloakadmin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "events_enabled", nullable = false)
    private Boolean eventsEnabled;

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

    @Column(name = "registration_allowed", nullable = false)
    private Boolean registrationAllowed;

    @Column(name = "remember_me", nullable = false)
    private Boolean rememberMe;

    @Column(name = "reset_password_allowed", nullable = false)
    private Boolean resetPasswordAllowed;

    @Column(name = "social", nullable = false)
    private Boolean social;

    @Column(name = "ssl_required")
    private String sslRequired;

    @Column(name = "sso_idle_timeout")
    private Integer ssoIdleTimeout;

    @Column(name = "sso_max_lifespan")
    private Integer ssoMaxLifespan;

    @Column(name = "update_profile_on_soc_login", nullable = false)
    private Boolean updateProfileOnSocLogin;

    @Column(name = "verify_email", nullable = false)
    private Boolean verifyEmail;

    @Column(name = "master_admin_client", length = 36)
    private String masterAdminClient;

    @Column(name = "login_lifespan")
    private Integer loginLifespan;

    @Column(name = "internationalization_enabled", nullable = false)
    private Boolean internationalizationEnabled;

    @Column(name = "default_locale")
    private String defaultLocale;

    @Column(name = "reg_email_as_username", nullable = false)
    private Boolean regEmailAsUsername;

    @Column(name = "admin_events_enabled", nullable = false)
    private Boolean adminEventsEnabled;

    @Column(name = "admin_events_details_enabled", nullable = false)
    private Boolean adminEventsDetailsEnabled;

    @Column(name = "edit_username_allowed", nullable = false)
    private Boolean editUsernameAllowed;

    @Column(name = "otp_policy_counter")
    private Integer otpPolicyCounter;

    @Column(name = "otp_policy_window")
    private Integer otpPolicyWindow;

    @Column(name = "otp_policy_period")
    private Integer otpPolicyPeriod;

    @Column(name = "otp_policy_digits")
    private Integer otpPolicyDigits;

    @Column(name = "otp_policy_alg", length = 36)
    private String otpPolicyAlg;

    @Column(name = "otp_policy_type", length = 36)
    private String otpPolicyType;

    @Column(name = "browser_flow", length = 36)
    private String browserFlow;

    @Column(name = "registration_flow", length = 36)
    private String registrationFlow;

    @Column(name = "direct_grant_flow", length = 36)
    private String directGrantFlow;

    @Column(name = "reset_credentials_flow", length = 36)
    private String resetCredentialsFlow;

    @Column(name = "client_auth_flow", length = 36)
    private String clientAuthFlow;

    @Column(name = "offline_session_idle_timeout")
    private Integer offlineSessionIdleTimeout;

    @Column(name = "revoke_refresh_token", nullable = false)
    private Boolean revokeRefreshToken;

    @Column(name = "access_token_life_implicit")
    private Integer accessTokenLifeImplicit;

    @Column(name = "login_with_email_allowed")
    private Boolean loginWithEmailAllowed;

    @Column(name = "duplicate_emails_allowed", nullable = false)
    private Boolean duplicateEmailsAllowed;

    @Column(name = "docker_auth_flow", length = 36)
    private String dockerAuthFlow;

    @Column(name = "refresh_token_max_reuse")
    private Integer refreshTokenMaxReuse;

    @Column(name = "allow_user_managed_access", nullable = false)
    private Boolean allowUserManagedAccess;

    @Column(name = "sso_max_lifespan_remember_me")
    private Integer ssoMaxLifespanRememberMe;

    @Column(name = "sso_idle_timeout_remember_me")
    private Integer ssoIdleTimeoutRememberMe;

    @Column(name = "default_role")
    private String defaultRole;
}
