import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:lista_contatos_flutter/dtos/contato_dto.dart';

class ContatoItem extends StatelessWidget {

  ContatoDTO contatoDTO;
  VoidCallback onClickItem;
  VoidCallback onDeletarContato;

  ContatoItem({ required this.contatoDTO, required this.onClickItem, required this.onDeletarContato });

  @override
  Widget build(BuildContext context) {

    return Card(
      margin: EdgeInsets.all(10),
      color: Colors.white,
      elevation: 5,
      child: InkWell(
        onTap: this.onClickItem == null ? () {} : this.onClickItem,
        child: Padding(
          padding: EdgeInsets.all(10),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Text(
                "${ contatoDTO.nome }",
                style: TextStyle(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                    fontSize: 20,
                    fontStyle: FontStyle.italic
                ),
              ),
              SizedBox(height: 7),
              Text("Telefone: ${ contatoDTO.telefone }"),
              Text("E-mail: ${ contatoDTO.email }"),
              Divider(height: 1),
              IconButton(
                icon: Icon(Icons.delete),
                onPressed: () {
                  this.onDeletarContato();
                },
              )
            ],
          ),
        ),
      )
    );
  }

}