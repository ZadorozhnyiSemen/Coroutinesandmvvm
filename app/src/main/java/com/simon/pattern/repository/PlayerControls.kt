package com.simon.pattern.repository

interface PlayerControls {
    suspend fun connect()
    suspend fun disconnect()
    suspend fun play(trackId: String)
}