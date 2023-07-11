package com.example.todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDB(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(
        context,
        TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_3_4)
        .build()

    return db
    }

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_3_4 = object : Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}
//Penggunaan integer bisa menjadi alternatif apabila status yang diperlukan tidak hanya true atau false saja, misalkan
//belum dikerjakan, sedang dikerjakan, dan sudah dikerjakan (meskipun pada kasus ini hanya menggunakan true & false
//yaitu belum dikerjakan dan sudah dikerjakan