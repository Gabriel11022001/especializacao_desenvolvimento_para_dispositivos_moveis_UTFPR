import 'package:app_estudos_gerenciamento_estados/provider/controller_provider.dart';
import 'package:flutter/material.dart';

class HomeCubit extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Cubit contador"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text("")
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {

        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
