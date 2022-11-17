package com.pandapp.preferenceapp.model

data class Rate(
    val uniName : String,
    val bolumName : String,
    var rate : Double,
    val userName : String
){
    constructor() : this("","",0.0,"")
}
