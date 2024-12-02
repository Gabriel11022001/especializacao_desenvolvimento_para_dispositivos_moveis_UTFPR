import 'dart:ffi';

import 'package:app_estudos_gerenciamento_estados/provider/controller_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class HomeProvider extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    TextEditingController _controllerCampoPeso = TextEditingController();
    TextEditingController _controllerCampoAltura = TextEditingController();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Provider contador"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Consumer<CounterControllerProvider>(builder: (context, value, child) => Text("${ value.contador }")),
            Text("Informe o peso:"),
            TextFormField(
              controller: _controllerCampoPeso,
            ),
            Text("Informe a altura:"),
            TextFormField(
              controller: _controllerCampoAltura,
            ),
            ElevatedButton(
              child: Text("Calcular IMC"),
              onPressed: () {
                // realizar o calculo do imc
                double peso = double.parse(_controllerCampoPeso.text.toString());
                double altura = double.parse(_controllerCampoAltura.text.toString());
                print("peso digitado: ${ peso }");
                print("altura digitada: ${ altura }");

                // efetivar o calculo do imc
                Provider.of<CounterControllerProvider>(context, listen: false)
                  .calcularImc(peso, altura);
              },
            ),
            Consumer<CounterControllerProvider>(builder: (context, value, child) => Text("IMC: ${ value.imc.toString() }"))
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Provider.of<CounterControllerProvider>(context, listen: false)
            .incrementarContador();
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
