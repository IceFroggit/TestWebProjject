package com.example.testwebprojject.auth

import android.net.Uri
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest

object AppAuth{
    private val serviceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(AuthConfig.AUTH_URI),
        Uri.parse(AuthConfig.TOKEN_URI),
        null,
        Uri.parse(AuthConfig.END_SESSION_URI)
    )
    fun getAuthRequest():AuthorizationRequest{
        val redirectUri =AuthConfig.CALLBACK_URL.toUri()
        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri).setScope(AuthConfig.SCOPE).build()
    }
    fun getEndSessionRequest():EndSessionRequest{
        return EndSessionRequest.Builder(serviceConfiguration)
            .setPostLogoutRedirectUri(AuthConfig.LOGOUT_CALLBACK_URL.toUri())
            .build()
    }

}
object AuthConfig {
    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val END_SESSION_URI = "https://github.com/logout"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"
    const val CLIENT_ID = "..."
    const val CLIENT_SECRET = "..."
    const val CALLBACK_URL = "ru.kts.oauth://github.com/callback"
    const val LOGOUT_CALLBACK_URL = "ru.kts.oauth://github.com/logout_callback"
}