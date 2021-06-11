package com.ludmilla.agenda

 class Fisica (nomePessoaFisica: String,
               celularPessoaFisica: String,
             val  referencia: String):
        Pessoa (nomePessoaFisica,
        celularPessoaFisica)
 {
     override fun toString(): String {
         return "Nome: $nome\nTelefone: $celular\nReferencia: $referencia\n"
     }
 }