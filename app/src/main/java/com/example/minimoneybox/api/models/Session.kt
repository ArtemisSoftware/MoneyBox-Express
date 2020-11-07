package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

class Session (

    @SerializedName("Session")
    val session: Session
)

class SessionInfo (

    @SerializedName("BearerToken")
    val bearerToken: String,

    @SerializedName("ExternalSessionId")
    val externalSessionId: String,

    @SerializedName("SessionExternalId")
    val sessionExternalId: String,

    @SerializedName("ExpiryInSeconds")
    val expiryInSeconds: Int
)
