import 'package:flutter/material.dart';
import 'package:lista_contatos_flutter/telas/tela_cadastro_contato.dart';
import 'package:lista_contatos_flutter/telas/tela_home.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lista de contatos',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.red),
        useMaterial3: true,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => Home(),
        '/cadastrar-contato': (context) => CadastroContato()
      },
    );
  }
}
