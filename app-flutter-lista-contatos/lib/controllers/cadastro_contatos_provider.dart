import 'package:flutter/cupertino.dart';

class CadastroContatoProvider extends ChangeNotifier {

  bool _apresentarLoaderConsultarCep = false;

  void apresentarLoaderConsultarCep() {
    this._apresentarLoaderConsultarCep = true;
    notifyListeners();
  }

  void esconderLoaderConsultarCep() {
    this._apresentarLoaderConsultarCep = false;
    notifyListeners();
  }

  bool get apresentLoaderConsultaCep => _apresentarLoaderConsultarCep;

}