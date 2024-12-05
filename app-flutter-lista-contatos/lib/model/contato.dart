import 'package:drift/drift.dart';
import 'package:drift_flutter/drift_flutter.dart';

part 'contato.g.dart';

class Contato extends Table {

  // colunas da tabela
  TextColumn get nome => text()();
  TextColumn get telefone => text()();
  TextColumn get email => text()();
  TextColumn get cep => text()();
  TextColumn get logradouro => text()();
  TextColumn get complemento => text()();
  TextColumn get bairro => text()();
  TextColumn get cidade => text()();
  TextColumn get numero => text()();

}

@DriftDatabase(tables: [Contato])
class AppDatabase extends _$AppDatabase {

  AppDatabase() : super(_openConnection());

  @override
  int get schemaVersion => 1;

  // método para criar o banco de dados
  static QueryExecutor _openConnection() {

    return driftDatabase(name: 'lista_contatos_database');
  }

  Future<void> deletarContatoPeloEmail(String email) async {
    // efetivando a deleção do contato na base de dados
    (delete(this.contato)..where((tabela) => tabela.email.equals(email))).go();
  }

}
