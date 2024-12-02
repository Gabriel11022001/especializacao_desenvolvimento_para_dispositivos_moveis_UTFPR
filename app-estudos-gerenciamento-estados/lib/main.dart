import 'package:app_estudos_gerenciamento_estados/config/routes.dart';
import 'package:app_estudos_gerenciamento_estados/cubit/CounterCubitController.dart';
import 'package:app_estudos_gerenciamento_estados/cubit/home.dart';
import 'package:app_estudos_gerenciamento_estados/provider/controller_provider.dart';
import 'package:app_estudos_gerenciamento_estados/provider/home.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: homeRoute,
      routes: {
        homeRoute: (context) => MyHomePage(),
        providerRoute: (context) => ChangeNotifierProvider(
          create: (context) => CounterControllerProvider(),
          child: HomeProvider(),
        ),
        cubitRoute: (context) => BlocProvider(
          create: (context) => CounterCubitController(),
          child: HomeCubit(),
        )
      },
    );
  }
}

class MyHomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Home"),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text("Provider"),
            onTap: () {
              // redirecionar para a tela HomeProvider
              Navigator.pushNamed(context, providerRoute);
            },
          ),
          ListTile(
            title: Text("Cubit"),
            onTap: () {
              // redirecionar para a tela HomeCubit
              Navigator.pushNamed(context, cubitRoute);
            },
          ),
          ListTile(
            title: Text("Getx"),
            onTap: () {
              // redirecionar para a tela HomeGetx

            },
          )
        ],
      )
    );
  }

}