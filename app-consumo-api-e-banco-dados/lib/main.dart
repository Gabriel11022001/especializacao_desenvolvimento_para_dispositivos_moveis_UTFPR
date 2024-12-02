import 'package:flutter/material.dart';
import 'package:app_consumo_api_e_banco_dados/map_location.dart';
import 'package:app_consumo_api_e_banco_dados/widgets/con_state.dart';
import 'package:app_consumo_api_e_banco_dados/widgets/offiline.dart';
import 'package:app_consumo_api_e_banco_dados/widgets/online.dart';

const homeRoute = "/home";
const mapRoute = "/map";

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'App consumo Retrofit',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: homeRoute,
      routes: {
        homeRoute: (context) => const MyHomePage(),
        // mapRoute: (context) => MapLocation()
      },
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {

  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() {

    return _MyHomePageState();
  }
}

class _MyHomePageState extends State<MyHomePage> {

  ConnectivityStatus _connectivityStatus = ConnectivityStatus.checking;

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("IP -> Geo"),
        actions: [
          TextButton(
              onPressed: () {},
              child: ConnState(
                callback: (status) {
                  setState(() {
                    _connectivityStatus = status;
                  });
                },
              ))
        ],
      ),
      body: Center(child: _getCurrentWidget()),
    );
  }

  Widget _getCurrentWidget() => switch (_connectivityStatus) {
    ConnectivityStatus.checking => const CircularProgressIndicator(),
    ConnectivityStatus.offline => const Offline(),
    ConnectivityStatus.online => Online(),
  };
}
