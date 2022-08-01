package space.eliseev.keycloakadmin.commons;

import lombok.Getter;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.RealmDto;

@Getter
@Component
public class GetObjectAsRow {
    private final String[] realmHeaders = new String[]{
            "Access Code Lifespan",
            "User Action Lifespan",
            "Access Token Lifespan",
            "Enabled",
            "Events Enabled",
            "Name",
            "Not Before",
            "Registration Allowed",
            "Remember Me",
            "Reset Password Allowed",
            "Social",
            "SSL Required",
            "Sso Idle Timeout",
            "Sso Max Lifespan",
            "Update Profile On Soc Login",
            "Verify Email",
            "Login Lifespan",
            "Internationalization Enabled",
            "Reg Email As Username",
            "Admin Events Enabled",
            "Admin Events Details Enabled",
            "Edit Username Allowed",
            "Otp Policy Counter",
            "Otp Policy Window",
            "Otp Policy Period",
            "Otp Policy Digits",
            "Offline Session Idle Timeout",
            "Revoke Refresh Token",
            "Access Token Life Implicit",
            "Login With Email Allowed",
            "Duplicate Emails Allowed",
            "Refresh Token Max Reuse",
            "Allow User Managed Access",
            "Sso Max Lifespan Remember Me",
            "Sso Idle Timeout Remember Me"
    };

    public String[] getRealmAsRow(RealmDto dto) {
        return new String[]{
                dto.getAccessCodeLifespan().toString(),
                dto.getUserActionLifespan().toString(),
                dto.getAccessTokenLifespan().toString(),
                dto.getEnabled().toString(),
                dto.getEventsEnabled().toString(),
                dto.getName(),
                dto.getNotBefore().toString(),
                dto.getRegistrationAllowed().toString(),
                dto.getRememberMe().toString(),
                dto.getResetPasswordAllowed().toString(),
                dto.getSocial().toString(),
                dto.getSslRequired(),
                dto.getSsoIdleTimeout().toString(),
                dto.getSsoMaxLifespan().toString(),
                dto.getUpdateProfileOnSocLogin().toString(),
                dto.getVerifyEmail().toString(),
                dto.getLoginLifespan().toString(),
                dto.getInternationalizationEnabled().toString(),
                dto.getRegEmailAsUsername().toString(),
                dto.getAdminEventsEnabled().toString(),
                dto.getAdminEventsDetailsEnabled().toString(),
                dto.getEditUsernameAllowed().toString(),
                dto.getOtpPolicyCounter().toString(),
                dto.getOtpPolicyWindow().toString(),
                dto.getOtpPolicyPeriod().toString(),
                dto.getOtpPolicyDigits().toString(),
                dto.getOfflineSessionIdleTimeout().toString(),
                dto.getRevokeRefreshToken().toString(),
                dto.getAccessTokenLifeImplicit().toString(),
                dto.getLoginWithEmailAllowed().toString(),
                dto.getDuplicateEmailsAllowed().toString(),
                dto.getRefreshTokenMaxReuse().toString(),
                dto.getAllowUserManagedAccess().toString(),
                dto.getSsoMaxLifespanRememberMe().toString(),
                dto.getSsoIdleTimeoutRememberMe().toString()
        };
    }
}
