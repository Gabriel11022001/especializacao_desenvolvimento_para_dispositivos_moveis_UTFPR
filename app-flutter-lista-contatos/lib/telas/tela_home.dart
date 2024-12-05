import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:lista_contatos_flutter/componentes/ContatoItem.dart';
import 'package:lista_contatos_flutter/controllers/home_provider.dart';
import 'package:lista_contatos_flutter/dtos/contato_dto.dart';
import 'package:lista_contatos_flutter/model/contato.dart';
import 'package:provider/provider.dart';

class Home extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => _HomeState();

}

class _HomeState extends State<Home> {

  final appBancoDados = AppDatabase();
  List<Widget> _contatos = [];
  bool _apresentarMensagemNaoExistemContatosCadastrados = false;

  @override
  void initState() {
    super.initState();
    // buscar os contatos na base de dados
    this._buscarContatos();
  }

  void _redirecionarTelaCadastrarContato(BuildContext contexto) {
    Navigator.pushNamed(contexto, "/cadastrar-contato")
      .then((_) { _buscarContatos(); });
  }

  // buscar os contatos na base de dados
  void _buscarContatos() async {
    List<ContatoData> contatosData = await this.appBancoDados.select(this.appBancoDados.contato)
        .get();

    bool apresentarMsgSemContatos = false;
    List<Widget> contatosApresentar = [];

    if (contatosData.length > 0) {
      apresentarMsgSemContatos = false;

      for (int contador = 0; contador < contatosData.length; contador++) {
        ContatoDTO contatoDTO = ContatoDTO();
        contatoDTO.nome = contatosData[ contador ].nome;
        contatoDTO.telefone = contatosData[ contador ].telefone;
        contatoDTO.email = contatosData[ contador ].email;
        contatoDTO.cep = contatosData[ contador ].cep;
        contatoDTO.complemento = contatosData[ contador ].complemento;
        contatoDTO.logradouro = contatosData[ contador ].logradouro;
        contatoDTO.cidade = contatosData[ contador ].cidade;
        contatoDTO.bairro = contatosData[ contador ].bairro;
        contatoDTO.numero = contatosData[ contador ].numero;

        ContatoItem contatoItem = ContatoItem(
            contatoDTO: contatoDTO,
            onClickItem: () {
              this._visualizarContato(contatoDTO);
            },
            onDeletarContato: () {
              print("Clicou no botão para deletar o contato ${ contatoDTO.email }");
              this._deletarContato(contatoDTO.email);
            },
        );

        contatosApresentar.add(contatoItem);
      }

    } else {
      apresentarMsgSemContatos = true;
    }

    // definir o estado
    setState(() {
      this._apresentarMensagemNaoExistemContatosCadastrados = apresentarMsgSemContatos;
      this._contatos = contatosApresentar;
    });

    print("Existem contatos cadastrados na base de dados? ${ this._apresentarMensagemNaoExistemContatosCadastrados }");
  }

  /*
  * nesse método, será apresentado um dialog para o usuário confirmar se deseja
  * ou não efetivar a deleção do contato
  * */
  void _deletarContato(String emailContato) async {

    return showDialog(
      context: context,
      barrierDismissible: true,
      builder: (BuildContext dialogContexto) {

        return AlertDialog(
          title: Text("Deletar contato", style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
          backgroundColor: Colors.red,
          content: SingleChildScrollView(
            padding: EdgeInsets.all(10),
            child: Column(
              children: [
                Text("Deseja mesmo deletar o contato com e-mail ${ emailContato }?", style: TextStyle(
                    color: Colors.white,
                    fontWeight: FontWeight.bold
                )),
                Divider(height: 20, color: Colors.transparent),
                ElevatedButton(
                  child: Text("Sim"),
                  onPressed: () {
                    this._efetivarDelecaoContato(emailContato);
                    Navigator.pop(context);
                  },
                ),
                Divider(height: 5, color: Colors.transparent),
                ElevatedButton(
                  child: Text("Não deletar"),
                  onPressed: () {
                    Navigator.pop(context);
                  },
                )
              ],
            ),
          )
        );
      }
    );
  }

  // método onde será efetivada a deleção do contato
  void _efetivarDelecaoContato(String emailContato) async {
    await this.appBancoDados.deletarContatoPeloEmail(emailContato);
    this._buscarContatos();
    Fluttertoast.showToast(
        msg: "Contato deletado com sucesso!",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.TOP_RIGHT,
        timeInSecForIosWeb: 2,
        backgroundColor: Colors.green,
        textColor: Colors.white,
        fontSize: 16
    );
  }

  // apresentar dialog com os dados do contato em questão
  Future<void> _visualizarContato(ContatoDTO contato) async {

    return showDialog(
      context: context,
      barrierDismissible: true,
      builder: (BuildContext buildContext) {

        return AlertDialog(
          title: Text("Dados do contato"),
          backgroundColor: Colors.white,
          content: SingleChildScrollView(
            child: Padding(
              padding: EdgeInsets.all(5),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text("Nome do contato: ${ contato.nome }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Telefone do contato: ${ contato.telefone }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("E-mail do contato: ${ contato.email }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Cep: ${ contato.cep }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Endereço: ${ contato.logradouro }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Complemento: ${ contato.complemento }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Bairro: ${ contato.bairro }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Cidade: ${ contato.cidade }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold)),
                  Text("Número: ${ contato.numero }", style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold))
                ],
              ),
            ),
          ),
        );
      }
    );
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text("Lista de contatos"),
        backgroundColor: Colors.red,
        titleTextStyle: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold),
      ),
      backgroundColor: Color.fromRGBO(241, 242, 246, 1),
      body: Padding(
        padding: EdgeInsets.all(18),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Lista de contatos", style: TextStyle(
                  color: Colors.red,
                  fontSize: 25,
                  fontWeight: FontWeight.bold,
                  fontStyle: FontStyle.italic
                )
              ),
              Divider(height: 50, color: Colors.transparent),
              Expanded(
                child: _apresentarMensagemNaoExistemContatosCadastrados ? Text("Não existem contatos!") : ListView.separated(
                  itemBuilder: (context, index) => _contatos[ index ],
                  separatorBuilder: (context, index) => const Divider(
                    height: 1,
                  ),
                  itemCount: _contatos.length,
                ),
              )
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {
          // redirecionar o usuário para a tela de cadastro de contato
          this._redirecionarTelaCadastrarContato(context);
        },
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
    );
  }

}