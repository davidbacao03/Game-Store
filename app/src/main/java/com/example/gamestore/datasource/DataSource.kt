package com.example.gamestore.datasource

import com.example.gamestore.R
import com.example.gamestore.models.StoreItem
import com.example.gamestore.models.VideoGame

object DataSource {
    
    fun obterJogosDisponiveis(): List<VideoGame> {
        return listOf(
            criarCounterStrike2(),
            criarEuroTruckSimulator2()
        )
    }
    
    private fun criarCounterStrike2(): VideoGame {
        val conteudos = listOf(
            StoreItem(
                itemId = 1,
                titulo = "Operation Pack 2024",
                descricao = "Novo operation pack com missões exclusivas, mapas competitivos inéditos e recompensas únicas. Inclui acesso a modos de jogo especiais e skins raras.",
                preco = 14.99,
                imagemId = R.drawable.cs2_dlc1
            ),
            StoreItem(
                itemId = 2,
                titulo = "Prime Status Upgrade",
                descricao = "Upgrade para conta Prime com matchmaking exclusivo, drops semanais de itens, e acesso a servidores dedicados com melhores jogadores.",
                preco = 19.99,
                imagemId = R.drawable.cs2_dlc2
            ),
            StoreItem(
                itemId = 3,
                titulo = "Ultimate Skins Bundle",
                descricao = "Pacote completo com skins premium para todas as armas principais. Inclui designs exclusivos da comunidade e efeitos visuais únicos.",
                preco = 49.99,
                imagemId = R.drawable.cs2_dlc3
            )
        )
        
        return VideoGame(
            gameId = 1,
            titulo = "Counter-Strike 2",
            descricaoCompleta = "O lendário FPS tático renovado com motor Source 2. Experimenta gráficos melhorados, física realista de fumo, e tickrate de servidor revolucionário. Compete em mapas clássicos redesenhados e domina o cenário competitivo global.",
            iconeResourceId = R.drawable.cs2_icon,
            capaResourceId = R.drawable.cs2_bg,
            itensDisponiveis = conteudos
        )
    }
    
    private fun criarEuroTruckSimulator2(): VideoGame {
        val conteudos = listOf(
            StoreItem(
                itemId = 4,
                titulo = "West Balkans Expansion",
                descricao = "Explora os Balcãs Ocidentais com mais de 8000 km de novas estradas. Descobre paisagens deslumbrantes, cidades autênticas e desafios únicos de condução pela região.",
                preco = 17.99,
                imagemId = R.drawable.ets2_dlc1
            ),
            StoreItem(
                itemId = 5,
                titulo = "Heavy Cargo Pack",
                descricao = "Transporta cargas especiais e sobredimensionadas. Requer licença especial e oferece pagamentos mais elevados. Inclui novos trailers e equipamento especializado.",
                preco = 8.99,
                imagemId = R.drawable.ets2_dlc2
            ),
            StoreItem(
                itemId = 6,
                titulo = "Ultimate Truck Collection",
                descricao = "Coleção definitiva com 12 camiões licenciados das maiores marcas europeias. Cada camião totalmente personalizável com centenas de opções de tuning e acessórios.",
                preco = 24.99,
                imagemId = R.drawable.ets2_dlc3
            )
        )
        
        return VideoGame(
            gameId = 2,
            titulo = "Euro Truck Simulator 2",
            descricaoCompleta = "O simulador de condução mais realista da Europa. Conduz pela Europa, gere a tua empresa de transportes, e expande o teu império logístico. Explora milhares de quilómetros de estradas autênticas e entrega cargas por dezenas de cidades europeias.",
            iconeResourceId = R.drawable.ets2_icon,
            capaResourceId = R.drawable.ets2_bg,
            itensDisponiveis = conteudos
        )
    }
}
