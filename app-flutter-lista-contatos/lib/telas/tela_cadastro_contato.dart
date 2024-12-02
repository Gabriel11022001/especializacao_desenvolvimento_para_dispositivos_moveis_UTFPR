import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_masked_text2/flutter_masked_text2.dart';
import 'package:lista_contatos_flutter/componentes/label.dart';

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

  // método para consultar o endereço do contato pelo cep para a api do ViaCEP
  void _consultarEnderecoContatoPeloCep() {

  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text("Cadastrar contato"),
      ),
      body: Padding(
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
            )
          ],
        ),
      ),
    );
  }

}