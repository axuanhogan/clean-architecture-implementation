package com.axuanhogan.common.util.encryption

import javax.crypto.*
import javax.crypto.spec.IvParameterSpec

object AESUtil {

    fun encrypt(algorithm: String, key: SecretKey, iv: IvParameterSpec, input: String): String {
        val cipher = initCipher(
            algorithm = algorithm,
            key = key,
            iv = iv
        )
        val encryptResult = cipher.doFinal(input.toByteArray())

        return bytesToHex(encryptResult)
    }

    fun decrypt(algorithm: String, key: SecretKey, iv: IvParameterSpec, input: String): String {
        val cipher = initCipher(
            algorithm = algorithm,
            key = key,
            iv = iv
        )
        val decryptResult = cipher.doFinal(hexToBytes(input))

        return String(decryptResult).trim()
    }

    private fun initCipher(algorithm: String, key: SecretKey, iv: IvParameterSpec): Cipher {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)

        return cipher
    }

    private fun bytesToHex(data: ByteArray): String {
        return data.joinToString(separator = "") {
            "%02x".format(it)
        }
    }

    private fun hexToBytes(data: String): ByteArray {
        return data.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }
}
