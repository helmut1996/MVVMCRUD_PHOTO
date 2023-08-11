package com.helcode.fotosqlite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_foto")
    var id: Long = 0L,
    @ColumnInfo("nombre")
    var name:String?=null,
    @ColumnInfo("apellido")
    var lastname:String?=null,
    @ColumnInfo("foto", typeAffinity = ColumnInfo.BLOB)
    val photo:ByteArray?=null
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastname != other.lastname) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo.contentEquals(other.photo)) return false
        } else if (other.photo != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (lastname?.hashCode() ?: 0)
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        return result
    }
}
