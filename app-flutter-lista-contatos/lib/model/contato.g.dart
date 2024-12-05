// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'contato.dart';

// ignore_for_file: type=lint
class $ContatoTable extends Contato with TableInfo<$ContatoTable, ContatoData> {
  @override
  final GeneratedDatabase attachedDatabase;
  final String? _alias;
  $ContatoTable(this.attachedDatabase, [this._alias]);
  static const VerificationMeta _nomeMeta = const VerificationMeta('nome');
  @override
  late final GeneratedColumn<String> nome = GeneratedColumn<String>(
      'nome', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _telefoneMeta =
      const VerificationMeta('telefone');
  @override
  late final GeneratedColumn<String> telefone = GeneratedColumn<String>(
      'telefone', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _emailMeta = const VerificationMeta('email');
  @override
  late final GeneratedColumn<String> email = GeneratedColumn<String>(
      'email', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _cepMeta = const VerificationMeta('cep');
  @override
  late final GeneratedColumn<String> cep = GeneratedColumn<String>(
      'cep', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _logradouroMeta =
      const VerificationMeta('logradouro');
  @override
  late final GeneratedColumn<String> logradouro = GeneratedColumn<String>(
      'logradouro', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _complementoMeta =
      const VerificationMeta('complemento');
  @override
  late final GeneratedColumn<String> complemento = GeneratedColumn<String>(
      'complemento', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _bairroMeta = const VerificationMeta('bairro');
  @override
  late final GeneratedColumn<String> bairro = GeneratedColumn<String>(
      'bairro', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _cidadeMeta = const VerificationMeta('cidade');
  @override
  late final GeneratedColumn<String> cidade = GeneratedColumn<String>(
      'cidade', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  static const VerificationMeta _numeroMeta = const VerificationMeta('numero');
  @override
  late final GeneratedColumn<String> numero = GeneratedColumn<String>(
      'numero', aliasedName, false,
      type: DriftSqlType.string, requiredDuringInsert: true);
  @override
  List<GeneratedColumn> get $columns => [
        nome,
        telefone,
        email,
        cep,
        logradouro,
        complemento,
        bairro,
        cidade,
        numero
      ];
  @override
  String get aliasedName => _alias ?? actualTableName;
  @override
  String get actualTableName => $name;
  static const String $name = 'contato';
  @override
  VerificationContext validateIntegrity(Insertable<ContatoData> instance,
      {bool isInserting = false}) {
    final context = VerificationContext();
    final data = instance.toColumns(true);
    if (data.containsKey('nome')) {
      context.handle(
          _nomeMeta, nome.isAcceptableOrUnknown(data['nome']!, _nomeMeta));
    } else if (isInserting) {
      context.missing(_nomeMeta);
    }
    if (data.containsKey('telefone')) {
      context.handle(_telefoneMeta,
          telefone.isAcceptableOrUnknown(data['telefone']!, _telefoneMeta));
    } else if (isInserting) {
      context.missing(_telefoneMeta);
    }
    if (data.containsKey('email')) {
      context.handle(
          _emailMeta, email.isAcceptableOrUnknown(data['email']!, _emailMeta));
    } else if (isInserting) {
      context.missing(_emailMeta);
    }
    if (data.containsKey('cep')) {
      context.handle(
          _cepMeta, cep.isAcceptableOrUnknown(data['cep']!, _cepMeta));
    } else if (isInserting) {
      context.missing(_cepMeta);
    }
    if (data.containsKey('logradouro')) {
      context.handle(
          _logradouroMeta,
          logradouro.isAcceptableOrUnknown(
              data['logradouro']!, _logradouroMeta));
    } else if (isInserting) {
      context.missing(_logradouroMeta);
    }
    if (data.containsKey('complemento')) {
      context.handle(
          _complementoMeta,
          complemento.isAcceptableOrUnknown(
              data['complemento']!, _complementoMeta));
    } else if (isInserting) {
      context.missing(_complementoMeta);
    }
    if (data.containsKey('bairro')) {
      context.handle(_bairroMeta,
          bairro.isAcceptableOrUnknown(data['bairro']!, _bairroMeta));
    } else if (isInserting) {
      context.missing(_bairroMeta);
    }
    if (data.containsKey('cidade')) {
      context.handle(_cidadeMeta,
          cidade.isAcceptableOrUnknown(data['cidade']!, _cidadeMeta));
    } else if (isInserting) {
      context.missing(_cidadeMeta);
    }
    if (data.containsKey('numero')) {
      context.handle(_numeroMeta,
          numero.isAcceptableOrUnknown(data['numero']!, _numeroMeta));
    } else if (isInserting) {
      context.missing(_numeroMeta);
    }
    return context;
  }

  @override
  Set<GeneratedColumn> get $primaryKey => const {};
  @override
  ContatoData map(Map<String, dynamic> data, {String? tablePrefix}) {
    final effectivePrefix = tablePrefix != null ? '$tablePrefix.' : '';
    return ContatoData(
      nome: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}nome'])!,
      telefone: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}telefone'])!,
      email: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}email'])!,
      cep: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}cep'])!,
      logradouro: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}logradouro'])!,
      complemento: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}complemento'])!,
      bairro: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}bairro'])!,
      cidade: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}cidade'])!,
      numero: attachedDatabase.typeMapping
          .read(DriftSqlType.string, data['${effectivePrefix}numero'])!,
    );
  }

  @override
  $ContatoTable createAlias(String alias) {
    return $ContatoTable(attachedDatabase, alias);
  }
}

class ContatoData extends DataClass implements Insertable<ContatoData> {
  final String nome;
  final String telefone;
  final String email;
  final String cep;
  final String logradouro;
  final String complemento;
  final String bairro;
  final String cidade;
  final String numero;
  const ContatoData(
      {required this.nome,
      required this.telefone,
      required this.email,
      required this.cep,
      required this.logradouro,
      required this.complemento,
      required this.bairro,
      required this.cidade,
      required this.numero});
  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    map['nome'] = Variable<String>(nome);
    map['telefone'] = Variable<String>(telefone);
    map['email'] = Variable<String>(email);
    map['cep'] = Variable<String>(cep);
    map['logradouro'] = Variable<String>(logradouro);
    map['complemento'] = Variable<String>(complemento);
    map['bairro'] = Variable<String>(bairro);
    map['cidade'] = Variable<String>(cidade);
    map['numero'] = Variable<String>(numero);
    return map;
  }

  ContatoCompanion toCompanion(bool nullToAbsent) {
    return ContatoCompanion(
      nome: Value(nome),
      telefone: Value(telefone),
      email: Value(email),
      cep: Value(cep),
      logradouro: Value(logradouro),
      complemento: Value(complemento),
      bairro: Value(bairro),
      cidade: Value(cidade),
      numero: Value(numero),
    );
  }

  factory ContatoData.fromJson(Map<String, dynamic> json,
      {ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return ContatoData(
      nome: serializer.fromJson<String>(json['nome']),
      telefone: serializer.fromJson<String>(json['telefone']),
      email: serializer.fromJson<String>(json['email']),
      cep: serializer.fromJson<String>(json['cep']),
      logradouro: serializer.fromJson<String>(json['logradouro']),
      complemento: serializer.fromJson<String>(json['complemento']),
      bairro: serializer.fromJson<String>(json['bairro']),
      cidade: serializer.fromJson<String>(json['cidade']),
      numero: serializer.fromJson<String>(json['numero']),
    );
  }
  @override
  Map<String, dynamic> toJson({ValueSerializer? serializer}) {
    serializer ??= driftRuntimeOptions.defaultSerializer;
    return <String, dynamic>{
      'nome': serializer.toJson<String>(nome),
      'telefone': serializer.toJson<String>(telefone),
      'email': serializer.toJson<String>(email),
      'cep': serializer.toJson<String>(cep),
      'logradouro': serializer.toJson<String>(logradouro),
      'complemento': serializer.toJson<String>(complemento),
      'bairro': serializer.toJson<String>(bairro),
      'cidade': serializer.toJson<String>(cidade),
      'numero': serializer.toJson<String>(numero),
    };
  }

  ContatoData copyWith(
          {String? nome,
          String? telefone,
          String? email,
          String? cep,
          String? logradouro,
          String? complemento,
          String? bairro,
          String? cidade,
          String? numero}) =>
      ContatoData(
        nome: nome ?? this.nome,
        telefone: telefone ?? this.telefone,
        email: email ?? this.email,
        cep: cep ?? this.cep,
        logradouro: logradouro ?? this.logradouro,
        complemento: complemento ?? this.complemento,
        bairro: bairro ?? this.bairro,
        cidade: cidade ?? this.cidade,
        numero: numero ?? this.numero,
      );
  ContatoData copyWithCompanion(ContatoCompanion data) {
    return ContatoData(
      nome: data.nome.present ? data.nome.value : this.nome,
      telefone: data.telefone.present ? data.telefone.value : this.telefone,
      email: data.email.present ? data.email.value : this.email,
      cep: data.cep.present ? data.cep.value : this.cep,
      logradouro:
          data.logradouro.present ? data.logradouro.value : this.logradouro,
      complemento:
          data.complemento.present ? data.complemento.value : this.complemento,
      bairro: data.bairro.present ? data.bairro.value : this.bairro,
      cidade: data.cidade.present ? data.cidade.value : this.cidade,
      numero: data.numero.present ? data.numero.value : this.numero,
    );
  }

  @override
  String toString() {
    return (StringBuffer('ContatoData(')
          ..write('nome: $nome, ')
          ..write('telefone: $telefone, ')
          ..write('email: $email, ')
          ..write('cep: $cep, ')
          ..write('logradouro: $logradouro, ')
          ..write('complemento: $complemento, ')
          ..write('bairro: $bairro, ')
          ..write('cidade: $cidade, ')
          ..write('numero: $numero')
          ..write(')'))
        .toString();
  }

  @override
  int get hashCode => Object.hash(nome, telefone, email, cep, logradouro,
      complemento, bairro, cidade, numero);
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is ContatoData &&
          other.nome == this.nome &&
          other.telefone == this.telefone &&
          other.email == this.email &&
          other.cep == this.cep &&
          other.logradouro == this.logradouro &&
          other.complemento == this.complemento &&
          other.bairro == this.bairro &&
          other.cidade == this.cidade &&
          other.numero == this.numero);
}

class ContatoCompanion extends UpdateCompanion<ContatoData> {
  final Value<String> nome;
  final Value<String> telefone;
  final Value<String> email;
  final Value<String> cep;
  final Value<String> logradouro;
  final Value<String> complemento;
  final Value<String> bairro;
  final Value<String> cidade;
  final Value<String> numero;
  final Value<int> rowid;
  const ContatoCompanion({
    this.nome = const Value.absent(),
    this.telefone = const Value.absent(),
    this.email = const Value.absent(),
    this.cep = const Value.absent(),
    this.logradouro = const Value.absent(),
    this.complemento = const Value.absent(),
    this.bairro = const Value.absent(),
    this.cidade = const Value.absent(),
    this.numero = const Value.absent(),
    this.rowid = const Value.absent(),
  });
  ContatoCompanion.insert({
    required String nome,
    required String telefone,
    required String email,
    required String cep,
    required String logradouro,
    required String complemento,
    required String bairro,
    required String cidade,
    required String numero,
    this.rowid = const Value.absent(),
  })  : nome = Value(nome),
        telefone = Value(telefone),
        email = Value(email),
        cep = Value(cep),
        logradouro = Value(logradouro),
        complemento = Value(complemento),
        bairro = Value(bairro),
        cidade = Value(cidade),
        numero = Value(numero);
  static Insertable<ContatoData> custom({
    Expression<String>? nome,
    Expression<String>? telefone,
    Expression<String>? email,
    Expression<String>? cep,
    Expression<String>? logradouro,
    Expression<String>? complemento,
    Expression<String>? bairro,
    Expression<String>? cidade,
    Expression<String>? numero,
    Expression<int>? rowid,
  }) {
    return RawValuesInsertable({
      if (nome != null) 'nome': nome,
      if (telefone != null) 'telefone': telefone,
      if (email != null) 'email': email,
      if (cep != null) 'cep': cep,
      if (logradouro != null) 'logradouro': logradouro,
      if (complemento != null) 'complemento': complemento,
      if (bairro != null) 'bairro': bairro,
      if (cidade != null) 'cidade': cidade,
      if (numero != null) 'numero': numero,
      if (rowid != null) 'rowid': rowid,
    });
  }

  ContatoCompanion copyWith(
      {Value<String>? nome,
      Value<String>? telefone,
      Value<String>? email,
      Value<String>? cep,
      Value<String>? logradouro,
      Value<String>? complemento,
      Value<String>? bairro,
      Value<String>? cidade,
      Value<String>? numero,
      Value<int>? rowid}) {
    return ContatoCompanion(
      nome: nome ?? this.nome,
      telefone: telefone ?? this.telefone,
      email: email ?? this.email,
      cep: cep ?? this.cep,
      logradouro: logradouro ?? this.logradouro,
      complemento: complemento ?? this.complemento,
      bairro: bairro ?? this.bairro,
      cidade: cidade ?? this.cidade,
      numero: numero ?? this.numero,
      rowid: rowid ?? this.rowid,
    );
  }

  @override
  Map<String, Expression> toColumns(bool nullToAbsent) {
    final map = <String, Expression>{};
    if (nome.present) {
      map['nome'] = Variable<String>(nome.value);
    }
    if (telefone.present) {
      map['telefone'] = Variable<String>(telefone.value);
    }
    if (email.present) {
      map['email'] = Variable<String>(email.value);
    }
    if (cep.present) {
      map['cep'] = Variable<String>(cep.value);
    }
    if (logradouro.present) {
      map['logradouro'] = Variable<String>(logradouro.value);
    }
    if (complemento.present) {
      map['complemento'] = Variable<String>(complemento.value);
    }
    if (bairro.present) {
      map['bairro'] = Variable<String>(bairro.value);
    }
    if (cidade.present) {
      map['cidade'] = Variable<String>(cidade.value);
    }
    if (numero.present) {
      map['numero'] = Variable<String>(numero.value);
    }
    if (rowid.present) {
      map['rowid'] = Variable<int>(rowid.value);
    }
    return map;
  }

  @override
  String toString() {
    return (StringBuffer('ContatoCompanion(')
          ..write('nome: $nome, ')
          ..write('telefone: $telefone, ')
          ..write('email: $email, ')
          ..write('cep: $cep, ')
          ..write('logradouro: $logradouro, ')
          ..write('complemento: $complemento, ')
          ..write('bairro: $bairro, ')
          ..write('cidade: $cidade, ')
          ..write('numero: $numero, ')
          ..write('rowid: $rowid')
          ..write(')'))
        .toString();
  }
}

abstract class _$AppDatabase extends GeneratedDatabase {
  _$AppDatabase(QueryExecutor e) : super(e);
  $AppDatabaseManager get managers => $AppDatabaseManager(this);
  late final $ContatoTable contato = $ContatoTable(this);
  @override
  Iterable<TableInfo<Table, Object?>> get allTables =>
      allSchemaEntities.whereType<TableInfo<Table, Object?>>();
  @override
  List<DatabaseSchemaEntity> get allSchemaEntities => [contato];
}

typedef $$ContatoTableCreateCompanionBuilder = ContatoCompanion Function({
  required String nome,
  required String telefone,
  required String email,
  required String cep,
  required String logradouro,
  required String complemento,
  required String bairro,
  required String cidade,
  required String numero,
  Value<int> rowid,
});
typedef $$ContatoTableUpdateCompanionBuilder = ContatoCompanion Function({
  Value<String> nome,
  Value<String> telefone,
  Value<String> email,
  Value<String> cep,
  Value<String> logradouro,
  Value<String> complemento,
  Value<String> bairro,
  Value<String> cidade,
  Value<String> numero,
  Value<int> rowid,
});

class $$ContatoTableFilterComposer
    extends Composer<_$AppDatabase, $ContatoTable> {
  $$ContatoTableFilterComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnFilters<String> get nome => $composableBuilder(
      column: $table.nome, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get telefone => $composableBuilder(
      column: $table.telefone, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get email => $composableBuilder(
      column: $table.email, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get cep => $composableBuilder(
      column: $table.cep, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get logradouro => $composableBuilder(
      column: $table.logradouro, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get complemento => $composableBuilder(
      column: $table.complemento, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get bairro => $composableBuilder(
      column: $table.bairro, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get cidade => $composableBuilder(
      column: $table.cidade, builder: (column) => ColumnFilters(column));

  ColumnFilters<String> get numero => $composableBuilder(
      column: $table.numero, builder: (column) => ColumnFilters(column));
}

class $$ContatoTableOrderingComposer
    extends Composer<_$AppDatabase, $ContatoTable> {
  $$ContatoTableOrderingComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  ColumnOrderings<String> get nome => $composableBuilder(
      column: $table.nome, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get telefone => $composableBuilder(
      column: $table.telefone, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get email => $composableBuilder(
      column: $table.email, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get cep => $composableBuilder(
      column: $table.cep, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get logradouro => $composableBuilder(
      column: $table.logradouro, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get complemento => $composableBuilder(
      column: $table.complemento, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get bairro => $composableBuilder(
      column: $table.bairro, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get cidade => $composableBuilder(
      column: $table.cidade, builder: (column) => ColumnOrderings(column));

  ColumnOrderings<String> get numero => $composableBuilder(
      column: $table.numero, builder: (column) => ColumnOrderings(column));
}

class $$ContatoTableAnnotationComposer
    extends Composer<_$AppDatabase, $ContatoTable> {
  $$ContatoTableAnnotationComposer({
    required super.$db,
    required super.$table,
    super.joinBuilder,
    super.$addJoinBuilderToRootComposer,
    super.$removeJoinBuilderFromRootComposer,
  });
  GeneratedColumn<String> get nome =>
      $composableBuilder(column: $table.nome, builder: (column) => column);

  GeneratedColumn<String> get telefone =>
      $composableBuilder(column: $table.telefone, builder: (column) => column);

  GeneratedColumn<String> get email =>
      $composableBuilder(column: $table.email, builder: (column) => column);

  GeneratedColumn<String> get cep =>
      $composableBuilder(column: $table.cep, builder: (column) => column);

  GeneratedColumn<String> get logradouro => $composableBuilder(
      column: $table.logradouro, builder: (column) => column);

  GeneratedColumn<String> get complemento => $composableBuilder(
      column: $table.complemento, builder: (column) => column);

  GeneratedColumn<String> get bairro =>
      $composableBuilder(column: $table.bairro, builder: (column) => column);

  GeneratedColumn<String> get cidade =>
      $composableBuilder(column: $table.cidade, builder: (column) => column);

  GeneratedColumn<String> get numero =>
      $composableBuilder(column: $table.numero, builder: (column) => column);
}

class $$ContatoTableTableManager extends RootTableManager<
    _$AppDatabase,
    $ContatoTable,
    ContatoData,
    $$ContatoTableFilterComposer,
    $$ContatoTableOrderingComposer,
    $$ContatoTableAnnotationComposer,
    $$ContatoTableCreateCompanionBuilder,
    $$ContatoTableUpdateCompanionBuilder,
    (ContatoData, BaseReferences<_$AppDatabase, $ContatoTable, ContatoData>),
    ContatoData,
    PrefetchHooks Function()> {
  $$ContatoTableTableManager(_$AppDatabase db, $ContatoTable table)
      : super(TableManagerState(
          db: db,
          table: table,
          createFilteringComposer: () =>
              $$ContatoTableFilterComposer($db: db, $table: table),
          createOrderingComposer: () =>
              $$ContatoTableOrderingComposer($db: db, $table: table),
          createComputedFieldComposer: () =>
              $$ContatoTableAnnotationComposer($db: db, $table: table),
          updateCompanionCallback: ({
            Value<String> nome = const Value.absent(),
            Value<String> telefone = const Value.absent(),
            Value<String> email = const Value.absent(),
            Value<String> cep = const Value.absent(),
            Value<String> logradouro = const Value.absent(),
            Value<String> complemento = const Value.absent(),
            Value<String> bairro = const Value.absent(),
            Value<String> cidade = const Value.absent(),
            Value<String> numero = const Value.absent(),
            Value<int> rowid = const Value.absent(),
          }) =>
              ContatoCompanion(
            nome: nome,
            telefone: telefone,
            email: email,
            cep: cep,
            logradouro: logradouro,
            complemento: complemento,
            bairro: bairro,
            cidade: cidade,
            numero: numero,
            rowid: rowid,
          ),
          createCompanionCallback: ({
            required String nome,
            required String telefone,
            required String email,
            required String cep,
            required String logradouro,
            required String complemento,
            required String bairro,
            required String cidade,
            required String numero,
            Value<int> rowid = const Value.absent(),
          }) =>
              ContatoCompanion.insert(
            nome: nome,
            telefone: telefone,
            email: email,
            cep: cep,
            logradouro: logradouro,
            complemento: complemento,
            bairro: bairro,
            cidade: cidade,
            numero: numero,
            rowid: rowid,
          ),
          withReferenceMapper: (p0) => p0
              .map((e) => (e.readTable(table), BaseReferences(db, table, e)))
              .toList(),
          prefetchHooksCallback: null,
        ));
}

typedef $$ContatoTableProcessedTableManager = ProcessedTableManager<
    _$AppDatabase,
    $ContatoTable,
    ContatoData,
    $$ContatoTableFilterComposer,
    $$ContatoTableOrderingComposer,
    $$ContatoTableAnnotationComposer,
    $$ContatoTableCreateCompanionBuilder,
    $$ContatoTableUpdateCompanionBuilder,
    (ContatoData, BaseReferences<_$AppDatabase, $ContatoTable, ContatoData>),
    ContatoData,
    PrefetchHooks Function()>;

class $AppDatabaseManager {
  final _$AppDatabase _db;
  $AppDatabaseManager(this._db);
  $$ContatoTableTableManager get contato =>
      $$ContatoTableTableManager(_db, _db.contato);
}
