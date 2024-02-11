package com.plg

enum class GuitarraModelos {
    Strato,
    Tele
}

enum class GuitarraBracos {
    Claro,
    Escuro
}

var modeloGuit = GuitarraModelos.Strato
var bracoGuit = GuitarraBracos.Escuro

fun escolherFotoBraco(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_braco
        GuitarraModelos.Tele -> R.drawable.tele_braco
        }
    }


fun escolherFotoCorpo(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_corpo
        GuitarraModelos.Tele -> R.drawable.tele_corpo
    }
}

fun escolherFotoHeadstock(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_headstock
        GuitarraModelos.Tele -> R.drawable.tele_headstock
    }
}

fun escolherFotoMarcacoes(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_marcacoes
        GuitarraModelos.Tele -> R.drawable.tele_marcacoes
    }
}

fun escolherFotoEscudo(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_escudo
        GuitarraModelos.Tele -> R.drawable.tele_escudo
    }
}

fun escolherFotoPecas(): Int {
    return when (modeloGuit) {
        GuitarraModelos.Strato -> R.drawable.strato_pecas
        GuitarraModelos.Tele -> R.drawable.tele_pecas
    }
}

fun desenharGuitarra() : List<Int>  {
    return listOf( escolherFotoCorpo(),
            escolherFotoBraco(),
            escolherFotoHeadstock(),
            escolherFotoEscudo(),
            escolherFotoMarcacoes(),
            escolherFotoPecas())
}