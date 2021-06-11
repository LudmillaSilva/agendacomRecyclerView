package com.ludmilla.agenda

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doAfterTextChanged


class MainActivity : AppCompatActivity() {
    lateinit var edtnome: EditText
    lateinit var celular: EditText
    lateinit var tipopessoa: RadioGroup
    lateinit var pessoafisica: RadioButton
    lateinit var pessoajuridica: RadioButton
    lateinit var pesquisa: EditText
    lateinit var pesquisar: Button
    lateinit var cadastrar: Button
    lateinit var referencia: EditText
    lateinit var email: EditText
    lateinit var listagem: TextView

    private var contatos: MutableList<Pessoa> = mutableListOf()

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtnome = findViewById(R.id.edtNome)
        celular = findViewById(R.id.edtCelular)
        celular.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        tipopessoa = findViewById(R.id.rdgTipoPessoa)
        pessoafisica = findViewById(R.id.rdPessoaFisica)
        pessoajuridica = findViewById(R.id.rdPessoaJuridica)
        referencia = findViewById(R.id.edtreferencia)
        email = findViewById(R.id.edtemail)
        pesquisa = findViewById(R.id.edtPesquisa)
        pesquisar = findViewById(R.id.btnPesquisar)
        cadastrar = findViewById(R.id.btnCadastrar)
        listagem = findViewById(R.id.txtListagem)



        tipopessoa.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (pessoafisica.id == radio.id) {
                referencia.visibility = View.VISIBLE
                email.visibility = View.GONE
            } else {
                email.visibility = View.VISIBLE
                referencia.visibility = View.GONE
            }
        }

        cadastrar.setOnClickListener {
            var contatosexibidos: String = ""
            val nomeinserido = edtnome.text.toString()
            val celularinserido = celular.text.toString()
            if (nomeinserido.isNotEmpty() && celularinserido.isNotEmpty())
            {

                if (pessoafisica.isChecked) {
                    val referenciainserida = referencia.text.toString()
                    if(referenciainserida.isEmpty()){ referencia.error= getString(R.string.referenciaError) }
                    else {var pessoaFisica = Fisica(nomePessoaFisica = nomeinserido,
                        celularPessoaFisica = celularinserido,
                        referencia = referenciainserida)
                    contatos.add(pessoaFisica)
                    referencia.setText("")}

                } else {
                    val emailinserido = email.text.toString()
                    if(emailinserido.isEmpty()) {email.error = getString(R.string.emailError)} else{
                    var pessoaJuridica = Juridica(nomePessoaJuridica = nomeinserido,
                        celularPessoaJuridica = celularinserido,
                        emailPessoaJuridica = emailinserido)
                    contatos.add(pessoaJuridica)
                    email.setText("")}
                }
                edtnome.setText("")
                celular.setText("")
                Toast.makeText(this, getString(R.string.nomeSalvo, nomeinserido), Toast.LENGTH_SHORT).show()
            }
            else {
                edtnome.error = getString(R.string.nomeError)
                celular.error = getString(R.string.telefoneError)
            }

            listagem.text = ordenarContatos()

        }

        pesquisar.setOnClickListener {
            val pesquisando = pesquisa.text.toString()
            //xuxa == contador (do for)
            val  resultado: List<Pessoa>  = contatos.filter { it.nome.contains(pesquisando) }
            var resultadoFiltrado: String = ""
            if (resultado!= null && resultado.isNotEmpty()){
                resultado.forEach { it -> resultadoFiltrado+= it.toString() }
                listagem.text = resultadoFiltrado

            }else {
                Toast.makeText(this,getString(R.string.pesquisaError), Toast.LENGTH_SHORT).show()
            }


        }

        pesquisa.doAfterTextChanged { it ->

            if(pesquisa.text.isEmpty()){
            listagem.text = ordenarContatos()
            }
       }

    }

    private fun ordenarContatos(): String {
        var contatosexibidos1: String =""
        val listaOrdenada = contatos.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.nome })
        )

        for (x in listaOrdenada) {
            contatosexibidos1 += x.toString()


        }

        return contatosexibidos1
    }


}





