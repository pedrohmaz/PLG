package com.plg

enum class GuitarraModelos {
    Strato,
    Tele
}

enum class GuitarraBracos {
    Claro,
    Escuro
}

enum class GuitarraCores {
    Vermelho,
    Verde,
    Creme,
    Sunburst
}

var modeloGuit = GuitarraModelos.Strato
var bracoGuit = GuitarraBracos.Escuro
var corGuit = GuitarraCores.Sunburst

fun escolherFotoBraco(): Int {

    return when (modeloGuit) {
        GuitarraModelos.Strato -> {
            when (bracoGuit) {
                GuitarraBracos.Claro -> R.drawable.braco_claro_strato
                GuitarraBracos.Escuro -> R.drawable.braco_escuro_strato
            }
        }

        GuitarraModelos.Tele -> {
            when (bracoGuit) {
                GuitarraBracos.Claro -> R.drawable.braco_claro_tele
                GuitarraBracos.Escuro -> R.drawable.braco_escuro_tele
            }
        }
    }

}

fun escolherFotoCorpo(): Int {

    return when (modeloGuit) {
        GuitarraModelos.Strato -> {
            when (corGuit) {
                GuitarraCores.Vermelho -> R.drawable.corpo_vermelho_strato
                GuitarraCores.Verde -> R.drawable.corpo_verde_strato
                GuitarraCores.Creme -> R.drawable.corpo_creme_strato
                GuitarraCores.Sunburst -> R.drawable.corpo_sunburst_strato
            }
        }

        GuitarraModelos.Tele -> {
            when (corGuit) {
                GuitarraCores.Vermelho -> R.drawable.corpo_vermelho_tele
                GuitarraCores.Verde -> R.drawable.corpo_verde_tele
                GuitarraCores.Creme -> R.drawable.corpo_creme_tele
                GuitarraCores.Sunburst -> R.drawable.corpo_sunburst_tele
            }
        }

    }
}