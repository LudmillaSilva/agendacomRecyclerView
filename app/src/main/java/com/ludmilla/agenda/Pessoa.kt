package com.ludmilla.agenda

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
 open class Pessoa (open val nome: String,
                    var celular: String): Parcelable{



}