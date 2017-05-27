package com.alexkaz.pictureviewer.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("created_at")
    @Expose
    private int createdAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public AuthResponse() {
    }

    /**
     *
     * @param scope
     * @param tokenType
     * @param accessToken
     * @param createdAt
     */
    public AuthResponse(String accessToken, String tokenType, String scope, int createdAt) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.createdAt = createdAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

}
