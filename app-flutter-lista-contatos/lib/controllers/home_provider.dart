import 'package:flutter/cupertino.dart';
import 'package:lista_contatos_flutter/dtos/contato_dto.dart';
import 'package:lista_contatos_flutter/repositorio/contato_repositorio.dart';

class HomeProvider extends ChangeNotifier {

  final ContatoRepositorio _contatoRepositorio = ContatoRepositorio();
  List<ContatoDTO> _contatosApresentar = [];
  bool _apresentarMensagenNaoExistemContatosCadastrados = false;

  Future<void> listarContatos() async {
    print("Caiu no método para listar os contatos e atualizar os estados!");
    List<ContatoDTO> contatos = await this._contatoRepositorio.buscarContatos();

    if (contatos.length > 0) {
      print("Existem contatos salvos na base de dados!");
      this._contatosApresentar = contatos;
      this._apresentarMensagenNaoExistemContatosCadastrados = false;
    } else {
      print("Não existem contatos salvos na base de dados!");
      this._contatosApresentar = [];
      this._apresentarMensagenNaoExistemContatosCadastrados = true;
    }

    notifyListeners();
  }

  List<ContatoDTO> get contatos => _contatosApresentar;
  bool get apresentarMsgNaoExistemContatosCadastradosBaseDados => _apresentarMensagenNaoExistemContatosCadastrados;

}