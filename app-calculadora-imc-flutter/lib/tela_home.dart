import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// criando minha primeira tela herdando de StatefulWidget
class TelaHome extends StatefulWidget {

  // construtor
  TelaHome({ super.key });

  /**
   * utlizar as controller para cada campo para controlar
   * seu valor
   */
  TextEditingController campoPesoController = TextEditingController();
  TextEditingController alturaController = TextEditingController();

  @override
  State<TelaHome> createState() {

    return _TelaHomeState();
  }
}

class _TelaHomeState extends State<TelaHome> {

  @override
  Widget build(BuildContext context) {

    // Column -> componente para posicionar os elementos um abaixo do outro
    return Padding(
      padding: EdgeInsets.all(10.0),
      child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [ // array contendo todos os elementos que vão ficar dentro do meu column
            // componente que representa um campo para digitar um texto
            TextFormField( // campo para o usuário informar seu peso
              controller: widget.campoPesoController,
              keyboardType: TextInputType.numberWithOptions(decimal: true),
              decoration: InputDecoration(
                  labelText: 'Informe seu peso:',
                  border: OutlineInputBorder()
              ),
            ),
            TextFormField( // campo para o usuário informar sua altura
              controller: widget.alturaController,
              keyboardType: TextInputType.numberWithOptions(decimal: true),
              decoration: InputDecoration(
                  labelText: 'Informe sua altura:',
                  border: OutlineInputBorder()
              ),
            ),
            OutlinedButton( // componente que representa um botão
              onPressed: () {
                // evento de quando o usuário clica no botão

              },
              child: Text("Calcular IMC"),
            ),
            Text("Resultado do calculo do IMC:")
          ]
      )
    );
  }

}
