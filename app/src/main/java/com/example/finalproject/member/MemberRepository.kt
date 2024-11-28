package com.example.finalproject.member

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.lang.reflect.Member

class MemberRepository(context: Context) {
    private val dbHelper = MemberDatabaseHelper(context)

    fun addMember(name: String, age: Int, id: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MemberDatabaseHelper.COLUMN_NAME, name)
            put(MemberDatabaseHelper.COLUMN_AGE, age)
            put(MemberDatabaseHelper.COLUMN_EMAIL, email)
        }
        return db.insert(MemberDatabaseHelper.TABLE_NAME, null, values).also {
            db.close()
        }
    }

    fun getAllMembers(): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            MemberDatabaseHelper.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val members = mutableListOf<Member>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(MemberDatabaseHelper.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(MemberDatabaseHelper.COLUMN_NAME))
                val age = getInt(getColumnIndexOrThrow(MemberDatabaseHelper.COLUMN_AGE))
                val email = getString(getColumnIndexOrThrow(MemberDatabaseHelper.COLUMN_EMAIL))
                members.add(Member(id, name, age, email))
            }
            close()
        }
        db.close()
        return members
    }
}
