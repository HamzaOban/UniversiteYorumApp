package com.pandapp.preferenceapp.model

data class User(
    var userName : String,
    var email : String,
    val password : String
){
    constructor() : this("","","")
}

