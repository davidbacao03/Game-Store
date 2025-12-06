package com.example.gamestore

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gamestore.componentes.DialogoCompra
import com.example.gamestore.componentes.ItemCompra
import com.example.gamestore.datasource.DataSource
import com.example.gamestore.models.StoreItem
import com.example.gamestore.models.VideoGame
import com.example.gamestore.ui.theme.GameStoreTheme

class DetalhesJogoActivity : ComponentActivity() {
    
    private lateinit var jogo: VideoGame
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        jogo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("JOGO", VideoGame::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("JOGO")
        } ?: run {
            finish()
            return
        }
        
        setContent {
            GameStoreTheme {
                TelaDetalhes(
                    jogo = jogo,
                    aoVoltar = { finish() },
                    aoComprar = { item ->
                        mostrarMensagemCompra(item)
                    }
                )
            }
        }
    }
    
    private fun mostrarMensagemCompra(item: StoreItem) {
        val msg = "Acabou de comprar o item ${item.titulo} por ${item.precoFormatado()}"
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun TelaDetalhes(
    jogo: VideoGame,
    aoVoltar: () -> Unit,
    aoComprar: (StoreItem) -> Unit
) {
    var itemSelecionado by remember { mutableStateOf<StoreItem?>(null) }
    var favorito by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            BarraTopoDetalhes(
                titulo = jogo.titulo,
                favorito = favorito,
                aoVoltar = aoVoltar,
                aoAlternarFavorito = { favorito = !favorito }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                InfoJogo(jogo = jogo)
            }
            
            item {
                Divider()
            }
            
            item {
                Text(
                    text = "Purchasable Items",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(jogo.itensDisponiveis) { item ->
                ItemCompra(
                    item = item,
                    aoSelecionar = { itemSelecionado = item }
                )
            }
        }
    }
    
    itemSelecionado?.let { item ->
        DialogoCompra(
            item = item,
            aoFechar = { itemSelecionado = null },
            aoConfirmar = {
                aoComprar(item)
                itemSelecionado = null
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraTopoDetalhes(
    titulo: String,
    favorito: Boolean,
    aoVoltar: () -> Unit,
    aoAlternarFavorito: () -> Unit
) {
    TopAppBar(
        title = { Text(text = titulo) },
        navigationIcon = {
            IconButton(onClick = aoVoltar) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            IconButton(onClick = aoAlternarFavorito) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (favorito) 
                        MaterialTheme.colorScheme.error 
                    else 
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Composable
fun InfoJogo(jogo: VideoGame) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = jogo.iconeResourceId),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            
            Text(
                text = jogo.descricaoCompleta,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
