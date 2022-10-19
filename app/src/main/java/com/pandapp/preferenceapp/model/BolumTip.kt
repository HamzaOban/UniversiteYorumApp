package com.pandapp.preferenceapp.model

data class BolumTip(
    val puan : Int,
    val siralama : Int,
    val bolumAdi : String,
    val burs : String,
    val bolumKodu : Int,

){
    constructor() : this(0,0,"","",0)
}
