import 'package:json_annotation/json_annotation.dart';

part 'endereco.g.dart';

@JsonSerializable()
class Endereco {

  const Endereco({ this.cep, this.logradouro, this.complemento, this.bairro, this.localidade });

  factory Endereco.fromJson(Map<String, dynamic> json) => _$EnderecoFromJson(json);

  final String? cep;
  final String? logradouro;
  final String? complemento;
  final String? bairro;
  final String? localidade;

  Endereco toJson() => _$EnderecoFromJson(this as Map<String, dynamic>);

}