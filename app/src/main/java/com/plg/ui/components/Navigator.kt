package com.plg.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plg.ui.screens.CriarUsuarioScreen
import com.plg.ui.screens.CustomizarInstrumentoScreen
import com.plg.ui.screens.DetalhesInstrumentoScreen
import com.plg.ui.screens.EditarInstrumentoScreen
import com.plg.ui.screens.ListaGuitarrasScreen
import com.plg.ui.screens.ListaUsuariosScreen
import com.plg.ui.screens.LoginScreen
import com.plg.ui.screens.SalvarInstrumentoScreen

@Composable
fun Navigator(activity: ComponentActivity) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    )
    {
        composable("login") {
            LoginScreen(activity = activity,
                aoNavegarParaCustomizarInstrumento =
                {
                    navController.navigate("customizarInstrumento")
                },
                aoNavegarParaCriarUsuario =
                {
                    navController.navigate("criarUsuario")
                },
                aoNavegarParaListaUsuarios = {
                    navController.navigate("listaUsuarios")
                }
            )
        }
        composable("criarUsuario") {
            CriarUsuarioScreen(
                activity = activity,
                navController = navController
            )
        }
        composable("customizarInstrumento") {
            CustomizarInstrumentoScreen(
                activity,
                aoNavegarParaListaGuitarras = { navController.navigate("listaGuitarras") },
                aoNavegarParaSalvarInstrumento = { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r ->
                    navController.navigate(
                        "salvarInstrumento/$a/$b/$c/$d/$e/$f/$g/$h/$i/$j/$k/$l/$m/$n/$o/$p/$q/$r"
                    )
                })
        }
        composable(
            "salvarInstrumento/{a}/{b}/{c}/{d}/{e}/{f}/{g}/{h}/{i}/{j}/{k}/{l}/{m}/{n}/{o}/{p}/{q}/{r}",
            arguments = listOf(
                navArgument("a") { type = NavType.IntType },
                navArgument("b") { type = NavType.IntType },
                navArgument("c") { type = NavType.IntType },
                navArgument("d") { type = NavType.IntType },
                navArgument("e") { type = NavType.IntType },
                navArgument("f") { type = NavType.IntType },
                navArgument("g") { type = NavType.IntType },
                navArgument("h") { type = NavType.IntType },
                navArgument("i") { type = NavType.IntType },
                navArgument("j") { type = NavType.IntType },
                navArgument("k") { type = NavType.IntType },
                navArgument("l") { type = NavType.FloatType },
                navArgument("m") { type = NavType.FloatType },
                navArgument("n") { type = NavType.FloatType },
                navArgument("o") { type = NavType.FloatType },
                navArgument("p") { type = NavType.FloatType },
                navArgument("q") { type = NavType.StringType },
                navArgument("r") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val a = backStackEntry.arguments?.getInt("a") ?: 0
            val b = backStackEntry.arguments?.getInt("b") ?: 0
            val c = backStackEntry.arguments?.getInt("c") ?: 0
            val d = backStackEntry.arguments?.getInt("d") ?: 0
            val e = backStackEntry.arguments?.getInt("e") ?: 0
            val f = backStackEntry.arguments?.getInt("f") ?: 0
            val g = backStackEntry.arguments?.getInt("g") ?: 0
            val h = backStackEntry.arguments?.getInt("h") ?: 0
            val i = backStackEntry.arguments?.getInt("i") ?: 0
            val j = backStackEntry.arguments?.getInt("j") ?: 0
            val k = backStackEntry.arguments?.getInt("k") ?: 0
            val l = backStackEntry.arguments?.getFloat("l") ?: 0f
            val m = backStackEntry.arguments?.getFloat("m") ?: 0f
            val n = backStackEntry.arguments?.getFloat("n") ?: 0f
            val o = backStackEntry.arguments?.getFloat("o") ?: 0f
            val p = backStackEntry.arguments?.getFloat("p") ?: 0f
            val q = backStackEntry.arguments?.getString("q") ?: ""
            val r = backStackEntry.arguments?.getString("r") ?: "0"

            SalvarInstrumentoScreen(
                activity = activity,
                navController = navController,
                aoNavegarParaListaGuitarras = {
                    navController.popBackStack()
                    navController.navigate("listaGuitarras")
                },
                a = a,
                b = b,
                c = c,
                d = d,
                e = e,
                f = f,
                g = g,
                h = h,
                i = i,
                j = j,
                k = k,
                l = l,
                m = m,
                n = n,
                o = o,
                p = p,
                q = q,
                r = r
            )
        }

        composable("listaGuitarras") {
            ListaGuitarrasScreen(activity = activity,
                aoNavegarParaDetalhesInstrumento = { id ->
                    navController.navigate("detalhesInstrumento/$id")
                },
                aoNavegarParaCustomizarInstrumento = {navController.navigate("customizarInstrumento")}
            )
        }

        composable(
            "detalhesInstrumento/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0
            DetalhesInstrumentoScreen(
                activity,
                navController,
                guitarraId = id,
                aoNavegarParaEditarInstrumento = {
                    navController.navigate("editarInstrumento")
                })
        }

        composable("editarInstrumento") {
            EditarInstrumentoScreen(
                activity = activity,
                aoNavegarParaSalvarInstrumento = { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r ->
                    navController.navigate(
                        "salvarInstrumento/$a/$b/$c/$d/$e/$f/$g/$h/$i/$j/$k/$l/$m/$n/$o/$p/$q/$r"
                    )
                })
        }

        composable("listaUsuarios"){
            ListaUsuariosScreen(activity = activity,
                aoNavegarParaListaGuitarras = {navController.navigate("listaGuitarras")})
        }

    }
}


