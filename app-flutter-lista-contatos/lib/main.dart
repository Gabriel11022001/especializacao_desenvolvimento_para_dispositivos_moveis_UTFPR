import 'package:flutter/material.dart';
import 'package:lista_contatos_flutter/controllers/cadastro_contatos_provider.dart';
import 'package:lista_contatos_flutter/controllers/home_provider.dart';
import 'package:lista_contatos_flutter/telas/tela_cadastro_contato.dart';
import 'package:lista_contatos_flutter/telas/tela_home.dart';
import 'package:provider/provider.dart';

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
        '/': (context) => ChangeNotifierProvider(
          create: (context) => HomeProvider(),
          child: Home(),
        ),
        '/cadastrar-contato': (context) => ChangeNotifierProvider(
          create: (context) => CadastroContatoProvider(),
          child: CadastroContato(),
        )
      },
    );
  }
}
