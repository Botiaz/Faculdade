import 'package:flutter/material.dart';
import 'services/csgo_service.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CSGO Skins Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const SkinsPage(),
    );
  }
}

class SkinsPage extends StatefulWidget {
  const SkinsPage({super.key});
  @override
  State<SkinsPage> createState() => _SkinsPageState();
}

class _SkinsPageState extends State<SkinsPage> {
  final CsgoService csgoService = CsgoService();
  late Future<List<dynamic>> skins;

  @override
  void initState() {
    super.initState();
    skins = csgoService.fetchSkins(lang: "en");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("CSGO Skins")),
      body: FutureBuilder<List<dynamic>>(
        future: skins,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text("Erro: ${snapshot.error}"));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text("Nenhuma skin encontrada"));
          } else {
            return ListView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: (context, index) {
                final skin = snapshot.data![index];
                final name = skin['name'] ?? "Sem nome";
                final imageUrl = skin['image'] ?? "";

                return Card(
                  margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 5),
                  child: ListTile(
                    leading: imageUrl.isNotEmpty
                      ? Image.network(imageUrl, width: 50, height: 50, fit: BoxFit.cover)
                      : const SizedBox(width: 50, height: 50),
                    title: Text(name),
                    subtitle: Text(skin['weapon']?['name'] ?? ""),
                  ),
                );
              },
            );
          }
        },
      ),
    );
  }
}
