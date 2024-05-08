package com.plg.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plg.ui.screens.CreateUserScreen
import com.plg.ui.screens.CustomizeInstrumentScreen
import com.plg.ui.screens.InstrumentDetailsScreen
import com.plg.ui.screens.EditInstrumentScreen
import com.plg.ui.screens.GuitarListScreen
import com.plg.ui.screens.UserListScreen
import com.plg.ui.screens.LoginScreen
import com.plg.ui.screens.SaveInstrumentScreen

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
                onNavigateToCustomizeInstrument =
                {
                    navController.navigate("customizarInstrumento")
                },
                onNavigateToCreateUser =
                {
                    navController.navigate("criarUsuario")
                },
                onNavigateToUserList = {
                    navController.navigate("listaUsuarios")
                }
            )
        }
        composable("criarUsuario") {
            CreateUserScreen(
                activity = activity,
                navController = navController
            )
        }
        composable("customizarInstrumento") {
            CustomizeInstrumentScreen(
                activity,
                onNavigateToGuitar = { navController.navigate("listaGuitarras") },
                onNavigateToSaveInstrument = { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s ->
                    navController.navigate(
                        "salvarInstrumento/$a/$b/$c/$d/$e/$f/$g/$h/$i/$j/$k/$l/$m/$n/$o/$p/$q/$r/$s"
                    )
                })
        }
        composable(
            "salvarInstrumento/{a}/{b}/{c}/{d}/{e}/{f}/{g}/{h}/{i}/{j}/{k}/{l}/{m}/{n}/{o}/{p}/{q}/{r}/{s}",
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
                navArgument("l") { type = NavType.IntType },
                navArgument("m") { type = NavType.FloatType },
                navArgument("n") { type = NavType.FloatType },
                navArgument("o") { type = NavType.FloatType },
                navArgument("p") { type = NavType.FloatType },
                navArgument("q") { type = NavType.FloatType },
                navArgument("r") { type = NavType.StringType },
                navArgument("s") { type = NavType.StringType }
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
            val l = backStackEntry.arguments?.getInt("l") ?: 0
            val m = backStackEntry.arguments?.getFloat("m") ?: 0f
            val n = backStackEntry.arguments?.getFloat("n") ?: 0f
            val o = backStackEntry.arguments?.getFloat("o") ?: 0f
            val p = backStackEntry.arguments?.getFloat("p") ?: 0f
            val q = backStackEntry.arguments?.getFloat("q") ?: 0f
            val r = backStackEntry.arguments?.getString("r") ?: ""
            val s = backStackEntry.arguments?.getString("s") ?: "0"

            SaveInstrumentScreen(
                activity = activity,
                navController = navController,
                onNavigateToGuitarList = {
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
                r = r,
                s = s
            )
        }

        composable("listaGuitarras") {
            GuitarListScreen(activity = activity,
                onNavigateToInstrumentDetails = { id ->
                    navController.navigate("detalhesInstrumento/$id")
                },
                onNavigateToCustomizeInstrument = {navController.navigate("customizarInstrumento")}
            )
        }

        composable(
            "detalhesInstrumento/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0
            InstrumentDetailsScreen(
                activity,
                navController,
                guitarId = id,
                onNavigateToEditInstrument = {
                    navController.navigate("editarInstrumento")
                })
        }

        composable("editarInstrumento") {
            EditInstrumentScreen(
                activity = activity,
                onNavigateToSaveInstrument = { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s ->
                    navController.navigate(
                        "salvarInstrumento/$a/$b/$c/$d/$e/$f/$g/$h/$i/$j/$k/$l/$m/$n/$o/$p/$q/$r/$s"
                    )
                })
        }

        composable("listaUsuarios"){
            UserListScreen(activity = activity,
                onNavigateToGuitarList = {navController.navigate("listaGuitarras")})
        }

    }
}


