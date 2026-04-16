package com.example.hw_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_3.ui.theme.AccentCyan
import com.example.hw_3.ui.theme.BackgroundSlate
import com.example.hw_3.ui.theme.HW3Theme
import com.example.hw_3.ui.theme.PrimaryBlue
import com.example.hw_3.ui.theme.SurfaceSlate
import com.example.hw_3.ui.theme.TextPrimary
import com.example.hw_3.ui.theme.TextSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HW3Theme {
                CvScreen()
            }
        }
    }
}

@Composable
fun CvScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundSlate)
    ) {
        CvHeader()
        CustomDivider()

        Spacer(modifier = Modifier.height(24.dp))

        PersonalInfo()

        Spacer(modifier = Modifier.height(24.dp))

        CustomDivider()

        Spacer(modifier = Modifier.height(24.dp))

        AboutMeSection()

        Spacer(modifier = Modifier.height(24.dp))

        CustomDivider()
        SkillsSection()
        CustomDivider()

    }
}


// İsmini CustomDivider yaptık ki orijinaliyle karışmasın
@Composable
fun CustomDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 48.dp), // Sağdan soldan boşluk
        thickness = 2.dp, // Kalınlık
        color = SurfaceSlate // Renk
    )
}

@Composable
fun CvHeader() {
    // Hem yazıyı hem de çizgiyi alt alta tutmak için tekrar Column kullanıyoruz
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "CV",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 16.dp),
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = AccentCyan,
        )
    }
}

@Composable
fun PersonalInfo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Emircan AÇAR",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Computer Engineer",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryBlue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Alanya Alaaddin Keykubat Unıversity",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SkillsSection() {
    val skills = listOf(
        "Kotlin", "Flutter", "C#", "ASP .NET Core",
        "PostgreSQL", "MSSQL", "Python",
        "Computer Vision", "Machine Learning",
        "SOLID", "Agile", "Clean Code",
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = "Yetenekler",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Yan yana tam 2 sütun olsun
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight() // Izgaraya bir alsan tanımlıyoruz
        ) {
            items(skills) { skill ->
                SkillItem(skillName = skill)
            }
        }
    }
}

// YETENEK ÇİPİ (Grid'e uyumlu olması için ortaladık ve genişlettik)
@Composable
fun SkillItem(skillName: String) {
    Surface (
        shape = RoundedCornerShape(12.dp),
        color = SurfaceSlate,
        modifier = Modifier.fillMaxWidth() // Izgara hücresini tamamen kapla
    ) {
        Text(
            text = skillName,
            modifier = Modifier.padding(vertical = 12.dp),
            textAlign = TextAlign.Center, // Yazıyı tam ortaya al
            color = AccentCyan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun AboutMeSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // Alt Başlık
        Text(
            text = "Hakkımda",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Metin Kutusu
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = SurfaceSlate, // Kutu için temanın yüzey rengini kullandık
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = ".NET ve mobil ekosistemlerinde deneyimli bir Bilgisayar Mühendisliği öğrencisiyim. Teknik yetkinliğimi 'Product Engineering' vizyonuyla birleştirerek ölçeklenebilir backend mimarileri ve kullanıcı odaklı çözümler geliştiriyorum. Karmaşık problemleri ürün değerine dönüştürme motivasyonuyla dinamik takımlarda rol almayı hedefliyorum.",
                modifier = Modifier.padding(16.dp), // Kutunun içinden boşluk
                color = TextSecondary,
                fontSize = 15.sp,
                lineHeight = 24.sp, // Satır aralarını açarak daha ferah bir okuma sağladık
                textAlign = TextAlign.Justify // Metni dergi gibi iki yana yasladık
            )
        }
    }
}