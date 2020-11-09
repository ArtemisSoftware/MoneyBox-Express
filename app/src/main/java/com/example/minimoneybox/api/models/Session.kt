package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

//{"User":{"UserId":"947002e4-4b6a-43ff-9f3d-0d939ef2dbaf","HasVerifiedEmail":true,"IsPinSet":true,"AmlStatus":"Clear","AmlAttempts":1,"RoundUpMode":"Off","JisaRoundUpMode":"Off","InvestorProduct":"","RegistrationStatus":"IsComplete","JisaRegistrationStatus":"None","DirectDebitMandateStatus":"Active","DateCreated":"2020-04-08T12:24:09.613","Animal":"Fox","ReferralCode":"LPUL98","IntercomHmac":"65daba4358d29dde9c7a453fc75fd323947e4f38fae8da2bc84204ec953a2293","IntercomHmaciOS":"65daba4358d29dde9c7a453fc75fd323947e4f38fae8da2bc84204ec953a2293","IntercomHmacAndroid":"166bf73be7a21bfde873dc01aec82c74575aead20077eefd6f7d55db8ef8a720","HasCompletedTutorial":false,"LastPayment":0.0,"PreviousMoneyboxAmount":0.00,"MoneyboxRegistrationStatus":"IsComplete","Email":"jaeren+androidtest@moneyboxapp.com","FirstName":"Jaeren","LastName":"Coathup","MobileNumber":"07812271271","RoundUpWholePounds":true,"DoubleRoundUps":false,"MoneyboxAmount":0.00,"InvestmentTotal":0.0,"CanReinstateMandate":false,"DirectDebitHasBeenSubmitted":true,"MonthlyBoostEnabled":false,"MonthlyBoostAmount":0.00,"MonthlyBoostDay":1,"RestrictedDevice":false,"Cohort":29},"Session":{"BearerToken":"3Zj1JTjYKTgR3vkZC/mtRBo9MLDJCw0gzPwrs1iBZJ8=","ExternalSessionId":"f81b154d-579b-410b-8c13-36de302c8fb5","SessionExternalId":"f81b154d-579b-410b-8c13-36de302c8fb5","ExpiryInSeconds":0},"ActionMessage":{"Type":"AddLink","Message":"Save little and often by rounding up your spending to the nearest pound.","Actions":[{"Label":"Connect an account","Amount":0.0,"Type":"AddLink","Animation":"None"}]},"InformationMessage":"2 days until collection"}


class Session (

    @SerializedName("Session")
    val session: SessionInfo
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
