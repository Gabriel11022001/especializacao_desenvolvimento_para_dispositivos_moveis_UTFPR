import 'dart:ffi';

import 'package:flutter/material.dart';

/**
 * Stateless -> têm mudança de estado
 * Statefull -> não têm mudança de estado
 */

// todo app flutter inicia a partir da função main
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  // construtor
  //{ parametro } -> define que o parâmetro é opcional
  const MyApp({ super.key });

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {

  // construtor
  const MyHomePage({ super.key, required this.title });

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();

}

class _MyHomePageState extends State<MyHomePage> {

  // _nome_da_propriedade -> propriedade privada
  int _counter = 0;

  void _incrementCounter() {

    // quando você invoca o setState, ele altera o estado de _counter e altera o valor no componente
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter, // evento do botão
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
