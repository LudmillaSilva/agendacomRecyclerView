package com.ludmilla.agenda

import kotlinx.parcelize.Parcelize

@Parcelize
class Juridica ( var nomePessoaJuridica: String,
                 var celularPessoaJuridica: String,
            val emailPessoaJuridica: String):

                Pessoa(nomePessoaJuridica,
                celularPessoaJuridica) {
    override fun toString(): String {
        return "Nome: $nome\nTelefone: $celular\nE-mail: $emailPessoaJuridica\n"
    }
}