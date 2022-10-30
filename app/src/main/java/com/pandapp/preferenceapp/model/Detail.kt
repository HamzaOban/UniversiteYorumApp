package com.pandapp.preferenceapp.model

data class Detail(
    var userName : String,
    var comment  : String
){
    constructor() : this("","")
}
