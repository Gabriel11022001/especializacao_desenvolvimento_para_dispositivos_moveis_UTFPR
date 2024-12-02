import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LabelProjeto extends StatelessWidget {

  final String textoLabel;

  const LabelProjeto({ super.key, required this.textoLabel });

  @override
  Widget build(BuildContext context) {

    return Text(
      textoLabel,
      style: TextStyle(
          color: Colors.red,
          fontWeight: FontWeight.bold,
          fontSize: 18
      ),
    );
  }

}