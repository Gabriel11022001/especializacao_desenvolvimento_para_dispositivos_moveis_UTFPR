import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:app_consumo_api_e_banco_dados/main.dart';
import 'package:app_consumo_api_e_banco_dados/source/local/database.dart';
import 'package:app_consumo_api_e_banco_dados/source/remote/rest_client.dart';

class Online extends StatelessWidget {

  Online({super.key});

  final TextEditingController _ipController = TextEditingController(text: "");

  @override
  Widget build(BuildContext context) {
    final dio = Dio();
    final client = RestClient(dio);

    final database = AppDatabase();

    return Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            TextFormField(
              controller: _ipController,
              maxLength: 15,
              decoration: const InputDecoration(
                labelText: 'Meu endereço ip:',
              ),
            ),
            TextButton(
                onPressed: () {
                  client.getGeoData(_ipController.text).then((geo) {
                    showBottomSheet(
                      enableDrag: true,
                      showDragHandle: true,
                      context: context,
                      builder: (context) {
                        return Wrap(
                          children: [
                            ListTile(
                              title: Text('City: ${geo.city}'),
                            ),
                            ListTile(
                              title: Text('Region Name: ${geo.regionName}'),
                            ),
                            ListTile(
                              title: Text('Country: ${geo.country}'),
                            ),
                            IconButton(
                                onPressed: () {
                                  /*Navigator.pushNamed(
                                      context,
                                      mapRoute,
                                      arguments: {
                                        "lat": geo.lat,
                                        "lon": geo.lon
                                      });*/
                                },
                                icon: const Icon(Icons.map_outlined))
                          ],
                        );
                      },
                    );

                    // salvando os dados na base de dados
                    database
                        .into(database.geoData)
                        .insert(GeoDataCompanion.insert(
                      ip: geo.query ?? "",
                      city: geo.city ?? "",
                      regionName: geo.regionName ?? "",
                      country: geo.country ?? "",
                      lat: "${geo.lat}",
                      lon: "${geo.lon}",
                    ));
                  });

                },
                child: const Text("Find"))
          ],
        ));
  }

}
