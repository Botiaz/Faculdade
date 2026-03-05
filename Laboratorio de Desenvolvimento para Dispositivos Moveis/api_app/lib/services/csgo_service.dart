import 'dart:convert';
import 'package:http/http.dart' as http;

class CsgoService {
  final String baseUrl = "https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api";

  Future<List<dynamic>> fetchSkins({String lang = "en"}) async {
    final url = "$baseUrl/$lang/skins.json";
    final response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      return jsonDecode(response.body) as List<dynamic>;
    } else {
      throw Exception("Falha ao carregar skins: ${response.statusCode}");
    }
  }
}
