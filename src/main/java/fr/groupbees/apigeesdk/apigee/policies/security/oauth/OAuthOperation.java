package fr.groupbees.apigeesdk.apigee.policies.security.oauth;

public enum OAuthOperation {
    GenerateAccessToken,
    GenerateAccessTokenImplicitGrant,
    GenerateAuthorizationCode,
    RefreshAccessToken,
    VerifyAccessToken,
    InvalidateToken,
    ValidateToken
}
