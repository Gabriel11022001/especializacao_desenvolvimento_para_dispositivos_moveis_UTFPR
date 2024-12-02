import 'package:dio/dio.dart';
import 'package:app_consumo_api_e_banco_dados/model/geo_data.dart';
import 'package:retrofit/retrofit.dart';

part 'rest_client.g.dart';

@RestApi(baseUrl: 'http://ip-api.com')
abstract class RestClient {

  factory RestClient(Dio dio, { String? baseUrl }) = _RestClient;

  // método para obter os dados do ip informado
  @GET('/json/{ip}')
  Future<GeoData> getGeoData(@Path() String ip);

}