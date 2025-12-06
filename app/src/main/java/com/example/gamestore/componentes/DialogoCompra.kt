package com.example.gamestore.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.models.StoreItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoCompra(
    item: StoreItem,
    aoFechar: () -> Unit,
    aoConfirmar: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = aoFechar,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.titulo,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imagemId),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                
                Text(
                    text = item.descricao,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.precoFormatado(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Button(
                    onClick = aoConfirmar,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Buy with 1-click",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialogoCompra() {
    MaterialTheme {
        DialogoCompra(
            item = StoreItem(
                itemId = 1,
                titulo = "Operation Pack 2024",
                descricao = "Novo pack com missões exclusivas",
                preco = 14.99,
                imagemId = android.R.drawable.ic_menu_gallery
            ),
            aoFechar = {},
            aoConfirmar = {}
        )
    }
}
