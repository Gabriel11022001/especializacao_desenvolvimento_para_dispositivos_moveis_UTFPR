import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_masked_text2/flutter_masked_text2.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:lista_contatos_flutter/componentes/label.dart';
import 'package:lista_contatos_flutter/dtos/contato_dto.dart';
import 'package:lista_contatos_flutter/repositorio/contato_repositorio.dart';

class CadastroContato extends StatelessWidget {

  // controllers dos campos
  TextEditingController _controllerNomeContato = TextEditingController();
  MaskedTextController _controllerTelefoneContato = MaskedTextController(mask: "(00) 00000-0000");
  TextEditingController _controllerEmailContato = TextEditingController();
  MaskedTextController _controllerCepEnderecoContato = MaskedTextController(mask: "00000-000");
  TextEditingController _controllerLogradouroEnderecoContato = TextEditingController();
  TextEditingController _controllerBairroEnderecoContato = TextEditingController();
  TextEditingController _controllerCidadeEnderecoContato = TextEditingController();
  TextEditingController _controllerComplementoEnderecoContato = TextEditingController();
  TextEditingController _controllerNumeroEnderecoContato = TextEditingController();
  final ContatoRepositorio _contatoRepositorio = ContatoRepositorio();

  // método para consultar o endereço do contato pelo cep para a api do ViaCEP
  void _consultarEnderecoContatoPeloCep() {

  }

  // apresentar alerta de erro ou sucesso
  void _apresentarAlerta(String mensagemAlerta, bool erro) {
    Fluttertoast.showToast(
      msg: mensagemAlerta,
      toastLength: Toast.LENGTH_SHORT,
      gravity: ToastGravity.TOP,
      timeInSecForIosWeb: 2,
      backgroundColor: erro == true ? Colors.red : Colors.green,
      textColor: Colors.white,
      fontSize: 16
    );
  }

  bool _validarFormularioCadastroContato() {
    bool ok = true;
    String nomeContato = this._controllerNomeContato.text.toString().trim();
    String telefoneContato = this._controllerTelefoneContato.text.toString().trim();
    String emailContato = this._controllerEmailContato.text.toString().trim();
    String cepEnderecoContato = this._controllerCepEnderecoContato.text.toString().trim();
    String logradouroEnderecoContato = this._controllerLogradouroEnderecoContato.text.toString().trim();
    String bairroEnderecoContato = this._controllerBairroEnderecoContato.text.toString().trim();
    String cidadeEnderecoContato = this._controllerCidadeEnderecoContato.text.toString().trim();
    String numeroEnderecoContato = this._controllerNumeroEnderecoContato.text.toString().trim();

    if (nomeContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o nome do contato!", true);
    } else if (telefoneContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o telefone do contato!", true);
    } else if (emailContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o e-mail do contato!", true);
    } else if (cepEnderecoContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o cep do endereço do contato!", true);
    } else if (logradouroEnderecoContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o logradouro do endereço do contato!", true);
    } else if (bairroEnderecoContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o bairro do endereço do contato!", true);
    } else if (cidadeEnderecoContato == "") {
      ok = false;
      this._apresentarAlerta("Informe a cidade do endereço do contato!", true);
    } else if (numeroEnderecoContato == "") {
      ok = false;
      this._apresentarAlerta("Informe o número do endereço do contato!", true);
    } else if (numeroEnderecoContato != "s/n") {
      
      if (int.parse(numeroEnderecoContato) <= 0) {
        ok = false;
        this._apresentarAlerta("Número inválido!", true);
      }
      
    }

    return ok;
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text("Cadastrar contato"),
        backgroundColor: Colors.red,
        titleTextStyle: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold),
        foregroundColor: Colors.white,
      ),
      backgroundColor: Color.fromRGBO(241, 242, 246, 1),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.all(18),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Center(
                  child: Text("Cadastro de contatos", textAlign: TextAlign.center, style: TextStyle(
                      color: Colors.red,
                      fontWeight: FontWeight.bold,
                      fontSize: 20
                  ))
              ),
              SizedBox(height: 30),
              LabelProjeto(textoLabel: "Nome do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o nome do contato
              TextFormField(
                controller: _controllerNomeContato,
                decoration: InputDecoration(
                    hintText: "Digite o nome do contato..."
                ),
                keyboardType: TextInputType.name,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Telefone do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o telefone do contato
              TextFormField(
                controller: _controllerTelefoneContato,
                decoration: InputDecoration(
                    hintText: "Digite o telefone do contato..."
                ),
                keyboardType: TextInputType.phone,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "E-mail do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o mail do contato
              TextFormField(
                controller: _controllerEmailContato,
                decoration: InputDecoration(
                    hintText: "Digite o e-mail do contato..."
                ),
                keyboardType: TextInputType.emailAddress,
              ),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  LabelProjeto(textoLabel: "Cep*"),
                  ElevatedButton(
                    child: Icon(Icons.search),
                    onPressed: () {
                      // pesquisar o endereço do contato pelo cep
                      _consultarEnderecoContatoPeloCep();
                    },
                  )
                ],
              ),
              SizedBox(height: 10),
              // campo para o usuário digitar o cep do endereço do contato
              TextFormField(
                controller: _controllerCepEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite o cep do endereço do contato..."
                ),
                keyboardType: TextInputType.number,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Logradouro do endereço do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o logradouro
              TextFormField(
                controller: _controllerLogradouroEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite o logradouro..."
                ),
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Complemento do endereço do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o complemento
              TextFormField(
                controller: _controllerComplementoEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite o complemento..."
                ),
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Bairro do endereço do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o bairro
              TextFormField(
                controller: _controllerBairroEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite o bairro..."
                ),
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Cidade do endereço do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar a cidade
              TextFormField(
                controller: _controllerCidadeEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite a cidade..."
                ),
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 20),
              LabelProjeto(textoLabel: "Número do endereço do contato*"),
              SizedBox(height: 10),
              // campo para o usuário digitar o número
              TextFormField(
                controller: _controllerNumeroEnderecoContato,
                decoration: InputDecoration(
                    hintText: "Digite o número..."
                ),
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 30),
              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  child: Text("Salvar contato"),
                  onPressed: () async {

                    try {
                      // salvar contato
                      print("Clicou no botão para salvar o contato");

                      if (this._validarFormularioCadastroContato()) {

                        // salvar o contato na base de dados

                        ContatoDTO contato = ContatoDTO();
                        contato.nome = this._controllerNomeContato.text.toString().trim();
                        contato.telefone = this._controllerTelefoneContato.text.toString().trim();
                        contato.email = this._controllerEmailContato.text.toString().trim();
                        contato.cep = this._controllerCepEnderecoContato.text.toString().trim();
                        contato.logradouro = this._controllerLogradouroEnderecoContato.text.toString().trim();
                        contato.complemento = this._controllerComplementoEnderecoContato.text.toString().trim();
                        contato.cidade = this._controllerCidadeEnderecoContato.text.toString().trim();
                        contato.bairro = this._controllerBairroEnderecoContato.text.toString().trim();
                        contato.numero = this._controllerNumeroEnderecoContato.text.toString().trim();

                        // persistindo o contato na base de dados
                        await this._contatoRepositorio.salvarContato(contato);

                        // limpar o formulário contendo os dados do contato
                        this._controllerNomeContato.clear();
                        this._controllerEmailContato.clear();
                        this._controllerTelefoneContato.clear();
                        this._controllerCepEnderecoContato.clear();
                        this._controllerComplementoEnderecoContato.clear();
                        this._controllerLogradouroEnderecoContato.clear();
                        this._controllerBairroEnderecoContato.clear();
                        this._controllerCidadeEnderecoContato.clear();
                        this._controllerNumeroEnderecoContato.clear();

                        this._apresentarAlerta("Contato salvo com sucesso!", false);
                      }

                    } catch (e) {
                      print("Erro: ${ e.toString() }");
                      this._apresentarAlerta("Erro: ${ e }", true);
                    }

                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.red,
                    foregroundColor: Colors.white,
                    padding: EdgeInsets.all(20),
                    textStyle: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold
                    )
                  ),
                ),
              )
            ],
          ),
        ),
      )
    );
  }

}