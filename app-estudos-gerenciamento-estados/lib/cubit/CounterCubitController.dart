import 'dart:ffi';

import 'package:flutter_bloc/flutter_bloc.dart';

class CounterCubitController extends Cubit<int> {

  CounterCubitController(): super(0);

  void incrementarValor() {
    emit(state + 1);
  }

}