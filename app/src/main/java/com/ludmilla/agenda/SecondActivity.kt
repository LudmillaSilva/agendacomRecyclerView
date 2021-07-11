package com.ludmilla.agenda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    lateinit var contatosAdapter: ContatosAdapter
    lateinit var rvContatos: RecyclerView

    // lateinit var listagem2: TextView
    lateinit var pesquisa: EditText
    lateinit var pesquisar: Button
    private var contatos2: MutableList<Pessoa> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        pesquisa = findViewById(R.id.edtPesquisa)
        pesquisar = findViewById(R.id.btnPesquisar)
        //listagem2 = findViewById(R.id.txtListagem2)
        rvContatos = findViewById(R.id.rvContatos)

        //val listaRecebida = intent.extras?.get("contatos")

        contatos2.addAll(MainActivity.contatos)
        //listagem2.text = MainActivity.ordenarContatos()
        contatos2 = MainActivity.ordenarContatos().toMutableList()
        contatosAdapter = ContatosAdapter(contatos2)
        rvContatos.adapter = contatosAdapter
        rvContatos.layoutManager = LinearLayoutManager(this)

        pesquisar.setOnClickListener {
            val pesquisando = pesquisa.text.toString()
            //xuxa == contador (do for)
            val resultado: List<Pessoa> =
                MainActivity.contatos.filter { it.nome.contains(pesquisando) }
            // var resultadoFiltrado: String = ""
            if (resultado != null && resultado.isNotEmpty()) {
                // for (contato in resultado){
                contatosAdapter.dataSetEscolha(resultado.toMutableList())
                /*     resultado.forEach { it -> resultadoFiltrado+= it.toString() }
                listagem2.text = resultadoFiltrado*/

            } else {
                Toast.makeText(this, getString(R.string.pesquisaError), Toast.LENGTH_SHORT).show()
            }


        }



        pesquisa.doAfterTextChanged { it ->

            if (pesquisa.text.isEmpty()) {
                 contatos2
            }

        }
    }
}