import 'package:lista_contatos_flutter/dtos/contato_dto.dart';
import 'package:lista_contatos_flutter/model/contato.dart';

class ContatoRepositorio {

  final _appDatabase = AppDatabase();

  // método da camada de repositório para validar se um contato já está cadastrado na base de dados com esse mesmo telefone
  Future<bool> _validarJaExisteContatoCadastradoComMesmoTelefone(String telefone) async {
    final contato = await (this._appDatabase.select(this._appDatabase.contato)..where((tabela) => tabela.telefone.equals(telefone))).get();

    return contato.isEmpty ? false : true;
  }

  // método da camada de repositório para validar se já existe um contato cadastrado com o mesmo e-mail testado
  Future<bool> _validarJaExisteContatoCadastradoComMesmoEmail(String email) async {
    final contato = await (this._appDatabase.select(this._appDatabase.contato)..where((tabela) => tabela.email.equals(email))).get();

    return contato.isEmpty ? false : true;
  }

  // método da camada de repositório para cadastrar um contato na base de dados
  Future<int> salvarContato(ContatoDTO contatoSalvar) async {
    bool jaExisteContatoCadastradoComTelefoneInformado = await this._validarJaExisteContatoCadastradoComMesmoTelefone(contatoSalvar.telefone);

    if (jaExisteContatoCadastradoComTelefoneInformado) {

      throw Exception("Já existe um contato cadastrado com esse telefone!");
    }

    bool jaExisteContatoCadastradoComEmailInformado = await this._validarJaExisteContatoCadastradoComMesmoEmail(contatoSalvar.email);

    if (jaExisteContatoCadastradoComEmailInformado) {

      throw new Exception("Já existe um contato cadastrado com esse e-mail!");
    }

    // persistindo o contato na base de dados
    return await this._appDatabase.
      into(this._appDatabase.contato).insert(ContatoCompanion.insert(
        nome: contatoSalvar.nome,
        telefone: contatoSalvar.telefone,
        email: contatoSalvar.email,
        cep: contatoSalvar.cep,
        complemento: contatoSalvar.complemento,
        logradouro: contatoSalvar.logradouro,
        cidade: contatoSalvar.cidade,
        bairro: contatoSalvar.bairro,
        numero: contatoSalvar.numero
    ));
  }

  // método da camada de repositório para buscar contatos cadastrados na base de dados
  Future<List<ContatoDTO>> buscarContatos() async {
    List<ContatoDTO> contatos = [];
    List<ContatoData> contatosBancoDados = await this._appDatabase.select(this._appDatabase.contato).get();

    for (ContatoData contato in contatosBancoDados) {
      ContatoDTO contatoDTO = ContatoDTO();
      contatoDTO.nome = contato.nome;
      contatoDTO.telefone = contato.telefone;
      contatoDTO.email = contato.email;
      contatoDTO.cep = contato.cep;
      contatoDTO.complemento = contato.complemento;
      contatoDTO.logradouro = contato.logradouro;
      contatoDTO.cidade = contato.cidade;
      contatoDTO.bairro = contato.bairro;
      contatoDTO.numero = contato.numero;

      contatos.add(contatoDTO);
    }

    return contatos;
  }

  // método da camada de repositório para deletar um contato da base de dados
  Future<void> deletarContato(String emailContatoDeletar) async {

  }

  // método da camada de repositório para buscar um contato pelo e-mail
  Future<ContatoDTO> buscarContatoPeloEmail(String emailContato) async {
    ContatoDTO contato = ContatoDTO();

    return contato;
  }

}