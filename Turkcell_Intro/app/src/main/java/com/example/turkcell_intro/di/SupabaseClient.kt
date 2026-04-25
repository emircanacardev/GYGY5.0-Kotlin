package com.example.turkcell_intro.di

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {

    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://mgziyoglcxwtijudogfa.supabase.co",
        supabaseKey = "sb_publishable_sH5gvOTKDILWFfZnUepQrQ_FHgC5jxI"
    ){
        install(Postgrest)
    }
}