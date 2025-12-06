package com.example.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamestore.componentes.CardJogo
import com.example.gamestore.datasource.DataSource
import com.example.gamestore.models.VideoGame
import com.example.gamestore.ui.theme.GameStoreTheme

class MainActivity : ComponentActivity() {
    
    private var listaJogos: List<VideoGame> = emptyList()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        listaJogos = DataSource.obterJogosDisponiveis()
        
        setContent {
            GameStoreTheme {
                TelaPrincipal(
                    jogos = listaJogos,
                    aoClicarJogo = { jogo ->
                        abrirDetalhes(jogo)
                    }
                )
            }
        }
    }
    
    private fun abrirDetalhes(jogo: VideoGame) {
        val intent = Intent(this, DetalhesJogoActivity::class.java)
        intent.putExtra("JOGO", jogo)
        startActivity(intent)
    }
}

@Composable
fun TelaPrincipal(
    jogos: List<VideoGame>,
    aoClicarJogo: (VideoGame) -> Unit
) {
    Scaffold(
        topBar = { BarraTopo() },
        bottomBar = { BarraInferior() }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(jogos) { jogo ->
                CardJogo(
                    jogo = jogo,
                    aoClicar = { aoClicarJogo(jogo) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraTopo() {
    TopAppBar(
        title = {
            Text(
                text = "Name of the company",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun BarraInferior() {
    var abaAtual by remember { mutableStateOf(0) }
    
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, null) },
            label = { Text("Featured") },
            selected = abaAtual == 0,
            onClick = { abaAtual = 0 }
        )
        
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, null) },
            label = { Text("History") },
            selected = abaAtual == 1,
            onClick = { abaAtual = 1 }
        )
        
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Profile") },
            selected = abaAtual == 2,
            onClick = { abaAtual = 2 }
        )
    }
}
