package space.eliseev.keycloakadmin.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RealmDto {

    private Integer accessCodeLifespan;

    private Integer userActionLifespan;

    private Integer accessTokenLifespan;

    private Boolean enabled;

    private Boolean eventsEnabled;

    private LocalDateTime eventsExpirationLocalDateTime;

    private String name;

    private Integer notBefore;

    private Boolean registrationAllowed;

    private Boolean rememberMe;

    private Boolean resetPasswordAllowed;

    private Boolean social;

    private String sslRequired;

    private Integer ssoIdleTimeout;

    private Integer ssoMaxLifespan;

    private Boolean updateProfileOnSocLogin;

    private Boolean verifyEmail;

    private Integer loginLifespan;

    private Boolean internationalizationEnabled;

    private Boolean regEmailAsUsername;

    private Boolean adminEventsEnabled;

    private Boolean adminEventsDetailsEnabled;

    private Boolean editUsernameAllowed;

    private Integer otpPolicyCounter;

    private Integer otpPolicyWindow;

    private Integer otpPolicyPeriod;

    private Integer otpPolicyDigits;

    private Integer offlineSessionIdleTimeout;

    private Boolean revokeRefreshToken;

    private Integer accessTokenLifeImplicit;

    private Boolean loginWithEmailAllowed;

    private Boolean duplicateEmailsAllowed;

    private Integer refreshTokenMaxReuse;

    private Boolean allowUserManagedAccess;

    private Integer ssoMaxLifespanRememberMe;

    private Integer ssoIdleTimeoutRememberMe;
}
