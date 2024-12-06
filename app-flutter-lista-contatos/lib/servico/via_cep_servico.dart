import 'package:dio/dio.dart';
import 'package:lista_contatos_flutter/model/endereco.dart';
import 'package:retrofit/retrofit.dart';

part 'via_cep_servico.g.dart';

@RestApi(baseUrl: "https://viacep.com.br/ws/")
abstract class ClienteRest {

  factory ClienteRest(Dio dio, { String? baseUrl }) = _ClienteRest;

  // método para buscar o endereço pelo cep
  @GET("{cep}/json")
  Future<Endereco> buscarEnderecoPeloCep(@Path("cep") String cep);

}