// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'endereco.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Endereco _$EnderecoFromJson(Map<String, dynamic> json) => Endereco(
      cep: json['cep'] as String?,
      logradouro: json['logradouro'] as String?,
      complemento: json['complemento'] as String?,
      bairro: json['bairro'] as String?,
      localidade: json['localidade'] as String?,
    );

Map<String, dynamic> _$EnderecoToJson(Endereco instance) => <String, dynamic>{
      'cep': instance.cep,
      'logradouro': instance.logradouro,
      'complemento': instance.complemento,
      'bairro': instance.bairro,
      'localidade': instance.localidade,
    };
