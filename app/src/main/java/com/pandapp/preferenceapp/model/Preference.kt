package com.pandapp.preferenceapp.model

data class Preference(

    val dil : BolumTip?,
    val esitAgirlik : BolumTip?,
    val sayisal : BolumTip?,
    val city : String?,
    val sozel : BolumTip?,
    val universityName : String?,
    val universityType : String?,

){
    constructor() : this(null, null, null,"", null,"","")
}