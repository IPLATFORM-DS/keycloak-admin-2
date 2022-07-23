package space.eliseev.keycloakadmin.dto;

import lombok.Data;


@Data
public class RealmDto {

    private Integer accessCodeLifespan;

    private Integer userActionLifespan;

    private Integer accessTokenLifespan;

    private boolean enabled;

    private boolean eventsEnabled;

    private Long eventsExpiration;

    private String name;

    private Integer notBefore;

    private boolean registrationAllowed;

    private boolean rememberMe;

    private boolean resetPasswordAllowed;

    private boolean social;

    private String sslRequired;

    private Integer ssoIdleTimeout;

    private Integer ssoMaxLifespan;

    private boolean updateProfileOnSocLogin;

    private boolean verifyEmail;

    private Integer loginLifespan;

    private boolean internationalizationEnabled;

    private boolean regEmailAsUsername;

    private boolean adminEventsEnabled;

    private boolean adminEventsDetailsEnabled;

    private boolean editUsernameAllowed;

    private Integer otpPolicyCounter;

    private Integer otpPolicyWindow;

    private Integer otpPolicyPeriod;

    private Integer otpPolicyDigits;

    private Integer offlineSessionIdleTimeout;

    private boolean revokeRefreshToken;

    private Integer accessTokenLifeImplicit;

    private boolean loginWithEmailAllowed;

    private boolean duplicateEmailsAllowed;

    private Integer refreshTokenMaxReuse;

    private boolean allowUserManagedAccess;

    private Integer ssoMaxLifespanRememberMe;

    private Integer ssoIdleTimeoutRememberMe;
}
