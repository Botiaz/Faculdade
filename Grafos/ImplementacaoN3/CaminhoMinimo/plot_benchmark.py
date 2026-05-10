import os
import matplotlib.pyplot as plt

def salvar_grafico(vertices, tempos, titulo, base_nome):
    fig, ax = plt.subplots(figsize=(7.2, 4.2))
    ax.plot(vertices, tempos, marker="o", linewidth=2)

    ax.set_title(titulo)
    ax.set_xlabel("Vertices")
    ax.set_ylabel("Tempo (ms)")
    ax.grid(True, linestyle="--", alpha=0.5)

    out_dir = os.path.dirname(os.path.abspath(__file__))
    pdf_path = os.path.join(out_dir, f"{base_nome}.pdf")
    png_path = os.path.join(out_dir, f"{base_nome}.png")

    fig.tight_layout()
    fig.savefig(pdf_path)
    fig.savefig(png_path, dpi=200)

    print(f"Grafico salvo em: {pdf_path}")
    print(f"Grafico salvo em: {png_path}")


def main():
    # Data from Benchmark output (tempo em ms)
    tipo1_vertices = [50, 500, 5000, 50000]
    tipo1_tempo = [0, 1, 8, 53]

    tipo2_vertices = [100, 900, 10000, 90000]
    tipo2_tempo = [0, 0, 2, 36]

    salvar_grafico(
        tipo1_vertices,
        tipo1_tempo,
        "Tempo vs Vertices - Tipo 1 (Aleatorio Esparso)",
        "grafico_tempo_tipo1",
    )

    salvar_grafico(
        tipo2_vertices,
        tipo2_tempo,
        "Tempo vs Vertices - Tipo 2 (Grid)",
        "grafico_tempo_tipo2",
    )


if __name__ == "__main__":
    main()
