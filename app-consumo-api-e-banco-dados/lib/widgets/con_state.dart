import 'package:flutter/material.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

enum ConnectivityStatus {
  checking,
  offline,
  online,
}

class ConnState extends StatefulWidget {

  ConnState({super.key, this.callback});

  Function(ConnectivityStatus status)? callback;

  @override
  State<ConnState> createState() => _ConnStateState();
}

class _ConnStateState extends State<ConnState> {

  final Connectivity _connectivity = Connectivity();
  ConnectivityStatus _connectivityStatus = ConnectivityStatus.checking;

  @override
  void initState() {
    super.initState();
    checkConnectivity();
  }

  void checkConnectivity() async {
    List<ConnectivityResult> result = await _connectivity.checkConnectivity();

    setState(() {

      if (result.contains(ConnectivityResult.none)) {
        _connectivityStatus = ConnectivityStatus.offline;
      } else {
        _connectivityStatus = ConnectivityStatus.online;
      }

    });

    widget.callback?.call(_connectivityStatus);
  }

  @override
  Widget build(BuildContext context) {

    return switch (_connectivityStatus) {
      ConnectivityStatus.checking => const Text('Checando...'),
      ConnectivityStatus.offline => const Text('Offline'),
      ConnectivityStatus.online => const Text('Online'),
    };
  }

}
