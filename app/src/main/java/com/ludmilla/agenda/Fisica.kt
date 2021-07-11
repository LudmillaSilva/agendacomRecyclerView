package com.ludmilla.agenda

import kotlinx.parcelize.Parcelize

@Parcelize
 class Fisica ( var nomePessoaFisica: String,
                var celularPessoaFisica: String,
             val  referencia: String):
        Pessoa (nomePessoaFisica,
        celularPessoaFisica)
 {
     override fun toString(): String {
         return "Nome: $nome\nTelefone: $celular\nReferencia: $referencia\n"
     }
 }