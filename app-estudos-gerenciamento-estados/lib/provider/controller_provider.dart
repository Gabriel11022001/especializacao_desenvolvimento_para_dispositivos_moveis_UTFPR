import 'package:flutter/cupertino.dart';

class CounterControllerProvider extends ChangeNotifier {

  int _contador = 0;
  double _imc = 0;

  void incrementarContador() {
    this._contador = this._contador + 1;
    notifyListeners();
  }

  void calcularImc(double peso, double altura) {
    this._imc = peso / (altura * altura);
    notifyListeners();
  }

  int get contador => _contador;
  double get imc => _imc;

}